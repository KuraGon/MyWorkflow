package com.saamp.workflow.service;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.mapper.PhoneContactMapper;
import com.saamp.workflow.mapper.PhoneUserMapper;
import com.saamp.workflow.repository.PhoneContactRepository;
import com.saamp.workflow.repository.PhoneUserRepository;
import com.workflow.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service applicatif PhoneContact.
 *
 * Contrat de filtrage :
 * - q = "" => pas de filtre.
 * - q non vide => filtre sur numéro / prénom / nom / société.
 *
 * Ce contrat est volontaire : il stabilise le typage SQL côté driver/Hibernate
 * et évite les problèmes Postgres autour des paramètres "NULL".
 */
@Service
@RequiredArgsConstructor
public class PhoneContactService {

    private final PhoneContactRepository phoneContactRepository;
    private final PhoneUserRepository phoneUserRepository;
    private final PhoneContactMapper phoneContactMapper;
    private final PhoneUserMapper phoneUserMapper;

    @Transactional(readOnly = true)
    public PhoneContactPageDTO getPage(String q, int page, int size) {
        PageRequest pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.asc("phoneNumber"), Sort.Order.asc("lastname"), Sort.Order.asc("firstname"))
        );

        String qNormalized = normalizeQueryOrEmpty(q);

        Page<PhoneContactEntity> res = phoneContactRepository.findPageByFilter(qNormalized, pageable);

        PhoneContactPageDTO dto = new PhoneContactPageDTO();
        dto.setMeta(toMeta(res));
        dto.setItems(res.getContent().stream().map(phoneContactMapper::toDto).toList());
        return dto;
    }

    @Transactional
    public PhoneContactDTO update(UUID id, PhoneContactUpdateRequestDTO request) {
        PhoneContactEntity entity = phoneContactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PhoneContact not found: " + id));

        phoneContactMapper.applyUpdate(request, entity);
        PhoneContactEntity saved = phoneContactRepository.save(entity);

        return phoneContactMapper.toDto(saved);
    }

    private static PageMetaDTO toMeta(Page<?> page) {
        PageMetaDTO meta = new PageMetaDTO();
        meta.setPage(page.getNumber());
        meta.setSize(page.getSize());
        meta.setTotalElements(page.getTotalElements());
        meta.setTotalPages(page.getTotalPages());
        meta.setHasNext(page.hasNext());
        meta.setHasPrev(page.hasPrevious());
        return meta;
    }

    private static String normalizeQueryOrEmpty(String q) {
        if (q == null) return "";
        String t = q.trim();
        return t.isEmpty() ? "" : t;
    }

    public PhoneContactDTO getPhoneContact(UUID id) {
        PhoneContactEntity res = this.phoneContactRepository.findById(id).orElse(null);
        if(res == null) return null;
        return phoneContactMapper.toDto(res);
    }

    public PhoneUserDTO getPhoneUserByPhoneContact(UUID phoneContactId) {
        PhoneContactEntity phoneContact = phoneContactRepository
                .findById(phoneContactId)
                .orElse(null);

        if (phoneContact == null) {
            return null;
        }

        return phoneUserRepository.findByPhoneContact(phoneContact)
                .map(phoneUserMapper::toDto)
                .orElse(null);
    }

}
