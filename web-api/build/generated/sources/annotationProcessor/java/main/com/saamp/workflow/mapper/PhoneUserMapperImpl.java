package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneUserEntity;
import com.workflow.dto.PhoneUserDTO;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T09:28:33+0100",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class PhoneUserMapperImpl implements PhoneUserMapper {

    @Autowired
    private PhoneContactMapper phoneContactMapper;

    @Override
    public PhoneUserDTO toDto(PhoneUserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneUserDTO phoneUserDTO = new PhoneUserDTO();

        phoneUserDTO.setId( entity.getId() );
        phoneUserDTO.setFirstname( entity.getFirstname() );
        phoneUserDTO.setLastname( entity.getLastname() );
        phoneUserDTO.setDenomination( entity.getDenomination() );
        phoneUserDTO.setCompany( entity.getCompany() );
        phoneUserDTO.setPhonePro( phoneContactMapper.toDto( entity.getPhonePro() ) );
        phoneUserDTO.setPhonePerso( phoneContactMapper.toDto( entity.getPhonePerso() ) );
        phoneUserDTO.setPhoneTelavox( phoneContactMapper.toDto( entity.getPhoneTelavox() ) );
        phoneUserDTO.setPhoneOther( phoneContactMapper.toDto( entity.getPhoneOther() ) );

        return phoneUserDTO;
    }
}
