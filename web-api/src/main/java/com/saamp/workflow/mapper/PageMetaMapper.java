package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneLogEntity;
import com.workflow.dto.PageMetaDTO;
import com.workflow.dto.PhoneLogDTO;
import com.workflow.dto.PhoneLogPageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses  = {PhoneLogMapper.class})
public interface PageMetaMapper {

    @Mapping(target = "meta.page", source = "number")
    @Mapping(target = "meta.size", source = "size")
    @Mapping(target = "meta.totalElements", source = "totalElements")
    @Mapping(target = "meta.totalPages", source = "totalPages")
    @Mapping(target = "meta.hasNext", expression = "java(page.hasNext())")
    @Mapping(target = "meta.hasPrev", expression = "java(page.hasPrevious())")
    @Mapping(target = "items", source = "content")
    PhoneLogPageDTO toDTO(Page<PhoneLogEntity> page);

    @Mapping(target = "meta.page", source = "number")
    @Mapping(target = "meta.size", source = "size")
    @Mapping(target = "meta.totalElements", source = "totalElements")
    @Mapping(target = "meta.totalPages", source = "totalPages")
    @Mapping(target = "meta.hasNext", expression = "java(page.hasNext())")
    @Mapping(target = "meta.hasPrev", expression = "java(page.hasPrevious())")
    @Mapping(target = "items", source = "content")
    PhoneLogPageDTO toPageDTO(Page<PhoneLogDTO> page);
}
