package com.saamp.workflow.mapper;

import com.saamp.workflow.repository.PhoneLogRepository;
import com.workflow.dto.SalesRepHourBucketDTO;
import com.workflow.dto.SalesRepKpiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalesRepStatsMapper {

    @Mapping(target = "totalCallDurationSec", expression = "java(nvl(p.getTotalCallDurationSec()))")
    @Mapping(target = "totalCalls", expression = "java(nvl(p.getTotalCalls()))")
    @Mapping(target = "totalCallsWeekEndAndNight", expression = "java(nvl(p.getTotalCallWeekendAndNight()))")
    @Mapping(target = "uniqueProspects", expression = "java(nvl(p.getUniqueProspects()))")
    @Mapping(target = "ratio", ignore = true)
    SalesRepKpiDTO toKpiDto(PhoneLogRepository.SalesRepKpiProjection p);

    default long nvl(Long v) {
        return v == null ? 0L : v;
    }

    default SalesRepHourBucketDTO toHourBucket(PhoneLogRepository.SalesRepHourActivityProjection p) {
        SalesRepHourBucketDTO b = new SalesRepHourBucketDTO();
        b.setHour(p.getHour());
        try {
            b.totalCount(p.getTotalCount() == null ? 0L : p.getTotalCount());
        } catch (Exception ignored) {}

        try {
            b.getClass().getMethod("setCallCount", Long.class).invoke(b, p.getCallCount() == null ? 0L : p.getCallCount());
        } catch (Exception ignored) {}
        try {
            b.getClass().getMethod("setSmsCount", Long.class).invoke(b, p.getSmsCount() == null ? 0L : p.getSmsCount());
        } catch (Exception ignored) {}
        try {
            b.getClass().getMethod("setTotal", Long.class).invoke(b, p.getTotalCount() == null ? 0L : p.getTotalCount());
        } catch (Exception ignored) {}

        return b;
    }
}
