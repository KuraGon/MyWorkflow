package com.saamp.workflow.repository.querydsl;

import com.saamp.workflow.entity.PhoneUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface PhoneUserQueryService {
    public Page<PhoneUserEntity> searchWithPredicate(String q, PageRequest pr, Long mysaampIdClient);

    public PhoneUserEntity searchDetailUser(UUID id);
}
