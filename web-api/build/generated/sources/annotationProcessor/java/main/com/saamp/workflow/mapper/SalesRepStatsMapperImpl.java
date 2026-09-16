package com.saamp.workflow.mapper;

import com.saamp.workflow.repository.PhoneLogRepository;
import com.workflow.dto.SalesRepKpiDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-15T17:27:42+0200",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class SalesRepStatsMapperImpl implements SalesRepStatsMapper {

    @Override
    public SalesRepKpiDTO toKpiDto(PhoneLogRepository.SalesRepKpiProjection p) {
        if ( p == null ) {
            return null;
        }

        SalesRepKpiDTO salesRepKpiDTO = new SalesRepKpiDTO();

        salesRepKpiDTO.setTotalCallDurationSec( nvl(p.getTotalCallDurationSec()) );
        salesRepKpiDTO.setTotalCalls( nvl(p.getTotalCalls()) );
        salesRepKpiDTO.setTotalCallsWeekEndAndNight( nvl(p.getTotalCallWeekendAndNight()) );
        salesRepKpiDTO.setUniqueProspects( nvl(p.getUniqueProspects()) );

        return salesRepKpiDTO;
    }
}
