package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.workflow.dto.PhoneContactDTO;
import com.workflow.dto.PhoneContactUpdateRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T09:28:32+0100",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class PhoneContactMapperImpl implements PhoneContactMapper {

    @Override
    public PhoneContactDTO toDto(PhoneContactEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneContactDTO phoneContactDTO = new PhoneContactDTO();

        phoneContactDTO.setId( entity.getId() );
        phoneContactDTO.setPhoneNumber( entity.getPhoneNumber() );

        return phoneContactDTO;
    }

    @Override
    public void applyUpdate(PhoneContactUpdateRequestDTO request, PhoneContactEntity entity) {
        if ( request == null ) {
            return;
        }

        entity.setPhoneNumber( request.getPhoneNumber() );
    }
}
