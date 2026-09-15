package com.saamp.workflow.mapper;

import com.saamp.workflow.repository.PhoneLogRepository;
import com.workflow.dto.CallCorrespondentStatsDTO;
import com.workflow.dto.SmsCorrespondentStatsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneLogStatsMapper {

    @Mapping(target = "number", source = "correspondent")
    @Mapping(target = "lastname", source = "correspondentLastName")
    @Mapping(target = "firstname", source = "correspondentFirstName")
    @Mapping(target = "company", source = "correspondentCompany")
    CallCorrespondentStatsDTO toCallDto(PhoneLogRepository.CallStatsAgg row);

    @Mapping(target = "number", source = "correspondent")
    @Mapping(target = "lastname", source = "correspondentLastName")
    @Mapping(target = "firstname", source = "correspondentFirstName")
    @Mapping(target = "company", source = "correspondentCompany")
    SmsCorrespondentStatsDTO toSmsDto(PhoneLogRepository.SmsStatsAgg row);
}
