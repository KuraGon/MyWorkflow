package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneLogEntity;
import com.workflow.dto.PhoneLogDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T09:28:33+0100",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class PhoneLogMapperImpl implements PhoneLogMapper {

    @Override
    public PhoneLogDTO toDto(PhoneLogEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneLogDTO phoneLogDTO = new PhoneLogDTO();

        phoneLogDTO.setForfaitMobile( entityForfaitContactPhoneNumber( entity ) );
        phoneLogDTO.setId( entity.getId() );
        phoneLogDTO.setType( entity.getType() );
        phoneLogDTO.setDirection( entity.getDirection() );
        phoneLogDTO.setZoneClient( entity.getZoneClient() );
        phoneLogDTO.setCallType( entity.getCallType() );
        phoneLogDTO.setDurationSec( entity.getDurationSec() );

        phoneLogDTO.setEventDate( entity.getEventDate() != null ? entity.getEventDate().atOffset(offset) : null );
        phoneLogDTO.setQuantityOctets( entity.getQuantityOctets() != null ? entity.getQuantityOctets().floatValue() : 0f );
        phoneLogDTO.setCostHt( entity.getCostHt() != null ? entity.getCostHt().floatValue() : 0f );

        return phoneLogDTO;
    }

    @Override
    public List<PhoneLogDTO> toDtos(List<PhoneLogEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PhoneLogDTO> list = new ArrayList<PhoneLogDTO>( entities.size() );
        for ( PhoneLogEntity phoneLogEntity : entities ) {
            list.add( toDto( phoneLogEntity ) );
        }

        return list;
    }

    private String entityForfaitContactPhoneNumber(PhoneLogEntity phoneLogEntity) {
        PhoneContactEntity forfaitContact = phoneLogEntity.getForfaitContact();
        if ( forfaitContact == null ) {
            return null;
        }
        return forfaitContact.getPhoneNumber();
    }
}
