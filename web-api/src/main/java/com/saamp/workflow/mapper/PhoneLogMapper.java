package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneLogEntity;
import com.workflow.dto.PhoneLogDTO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneLogMapper {
    ZoneOffset offset = ZoneOffset.UTC;

    @Mapping(target = "forfaitMobile", source = "forfaitContact.phoneNumber")
    @Mapping(
            target = "eventDate",
            expression = "java(entity.getEventDate() != null ? entity.getEventDate().atOffset(offset) : null)"
    )
    @Mapping(
            target = "quantityOctets",
            expression = "java(entity.getQuantityOctets() != null ? entity.getQuantityOctets().floatValue() : 0f)"
    )
    @Mapping(
            target = "costHt",
            expression = "java(entity.getCostHt() != null ? entity.getCostHt().floatValue() : 0f)"
    )
    PhoneLogDTO toDto(PhoneLogEntity entity);

    List<PhoneLogDTO> toDtos(List<PhoneLogEntity> entities);
}
