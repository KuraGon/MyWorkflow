package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.workflow.dto.PhoneContactDTO;
import com.workflow.dto.PhoneUserLiteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneUserLiteMapper {

    @Mapping(target = "phone", source = "contact")
    @Mapping(target = "id", source = "user.id")
    PhoneUserLiteDTO toLiteDto(
            PhoneUserEntity user,
            PhoneContactEntity contact
    );
}