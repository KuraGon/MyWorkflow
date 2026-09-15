package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.workflow.dto.PhoneContactDTO;
import com.workflow.dto.PhoneUserLiteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T09:28:32+0100",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class PhoneUserLiteMapperImpl implements PhoneUserLiteMapper {

    @Override
    public PhoneUserLiteDTO toLiteDto(PhoneUserEntity user, PhoneContactEntity contact) {
        if ( user == null && contact == null ) {
            return null;
        }

        PhoneUserLiteDTO phoneUserLiteDTO = new PhoneUserLiteDTO();

        if ( user != null ) {
            phoneUserLiteDTO.setId( user.getId() );
            phoneUserLiteDTO.setFirstname( user.getFirstname() );
            phoneUserLiteDTO.setLastname( user.getLastname() );
            phoneUserLiteDTO.setDenomination( user.getDenomination() );
            phoneUserLiteDTO.setCompany( user.getCompany() );
        }
        phoneUserLiteDTO.setPhone( phoneContactEntityToPhoneContactDTO( contact ) );

        return phoneUserLiteDTO;
    }

    protected PhoneContactDTO phoneContactEntityToPhoneContactDTO(PhoneContactEntity phoneContactEntity) {
        if ( phoneContactEntity == null ) {
            return null;
        }

        PhoneContactDTO phoneContactDTO = new PhoneContactDTO();

        phoneContactDTO.setId( phoneContactEntity.getId() );
        phoneContactDTO.setPhoneNumber( phoneContactEntity.getPhoneNumber() );

        return phoneContactDTO;
    }
}
