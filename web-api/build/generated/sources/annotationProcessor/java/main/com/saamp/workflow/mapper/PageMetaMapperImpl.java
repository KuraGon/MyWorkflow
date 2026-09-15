package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneLogEntity;
import com.workflow.dto.PageMetaDTO;
import com.workflow.dto.PhoneLogDTO;
import com.workflow.dto.PhoneLogPageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T09:28:32+0100",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class PageMetaMapperImpl implements PageMetaMapper {

    @Autowired
    private PhoneLogMapper phoneLogMapper;

    @Override
    public PhoneLogPageDTO toDTO(Page<PhoneLogEntity> page) {
        if ( page == null ) {
            return null;
        }

        PhoneLogPageDTO phoneLogPageDTO = new PhoneLogPageDTO();

        phoneLogPageDTO.setMeta( phoneLogEntityPageToPageMetaDTO( page ) );
        if ( page.hasContent() ) {
            phoneLogPageDTO.setItems( phoneLogMapper.toDtos( page.getContent() ) );
        }

        return phoneLogPageDTO;
    }

    @Override
    public PhoneLogPageDTO toPageDTO(Page<PhoneLogDTO> page) {
        if ( page == null ) {
            return null;
        }

        PhoneLogPageDTO phoneLogPageDTO = new PhoneLogPageDTO();

        phoneLogPageDTO.setMeta( phoneLogDTOPageToPageMetaDTO( page ) );
        if ( page.hasContent() ) {
            List<PhoneLogDTO> list = page.getContent();
            phoneLogPageDTO.setItems( new ArrayList<PhoneLogDTO>( list ) );
        }

        return phoneLogPageDTO;
    }

    protected PageMetaDTO phoneLogEntityPageToPageMetaDTO(Page<PhoneLogEntity> page) {
        if ( page == null ) {
            return null;
        }

        PageMetaDTO pageMetaDTO = new PageMetaDTO();

        pageMetaDTO.setPage( page.getNumber() );
        pageMetaDTO.setSize( page.getSize() );
        pageMetaDTO.setTotalElements( page.getTotalElements() );
        pageMetaDTO.setTotalPages( page.getTotalPages() );

        pageMetaDTO.setHasNext( page.hasNext() );
        pageMetaDTO.setHasPrev( page.hasPrevious() );

        return pageMetaDTO;
    }

    protected PageMetaDTO phoneLogDTOPageToPageMetaDTO(Page<PhoneLogDTO> page) {
        if ( page == null ) {
            return null;
        }

        PageMetaDTO pageMetaDTO = new PageMetaDTO();

        pageMetaDTO.setPage( page.getNumber() );
        pageMetaDTO.setSize( page.getSize() );
        pageMetaDTO.setTotalElements( page.getTotalElements() );
        pageMetaDTO.setTotalPages( page.getTotalPages() );

        pageMetaDTO.setHasNext( page.hasNext() );
        pageMetaDTO.setHasPrev( page.hasPrevious() );

        return pageMetaDTO;
    }
}
