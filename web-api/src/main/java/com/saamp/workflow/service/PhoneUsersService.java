package com.saamp.workflow.service;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.saamp.workflow.mapper.PhoneUserMapper;
import com.saamp.workflow.repository.PhoneUserRepository;
import com.saamp.workflow.repository.querydsl.PhoneUserQueryService;
import com.workflow.dto.PageMetaDTO;
import com.workflow.dto.PhoneContactDTO;
import com.workflow.dto.PhoneUserDTO;
import com.workflow.dto.PhoneUserPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneUsersService {

    private final PhoneUserQueryService phoneUserQueryService;
    private final PhoneUserMapper phoneUserMapper;

    @Transactional(readOnly = true)
    public PhoneUserDTO getUserDetail(UUID id){
        return phoneUserMapper.toDto(phoneUserQueryService.searchDetailUser(id));
    }

    @Transactional(readOnly = true)
    public PhoneUserPageDTO getPhoneUsersPage(Integer page, Integer size, String q, Long mysaampIdClient) {
        int p = page == null ? 0 : Math.max(0, page);
        int s = size == null ? 25 : Math.min(Math.max(1, size), 200);

        PageRequest pr = PageRequest.of(
                p,
                s,
                Sort.by(
                        Sort.Order.asc("lastname"),
                        Sort.Order.asc("firstname"),
                        Sort.Order.asc("denomination"),
                        Sort.Order.asc("company")
                )
        );

        Page<PhoneUserEntity> result = phoneUserQueryService.searchWithPredicate(q, pr,mysaampIdClient);

        PhoneUserPageDTO dto = new PhoneUserPageDTO();
        PageMetaDTO meta = new PageMetaDTO();
        meta.setPage(result.getNumber());
        meta.setSize(result.getSize());
        meta.setTotalElements(result.getTotalElements());
        meta.setTotalPages(result.getTotalPages());

        List<PhoneUserDTO> content = result.getContent().stream()
                .map(phoneUserMapper::toDto)
                .toList();

        dto.setMeta(meta);
        dto.setItems(content);
        return dto;
    }
}
