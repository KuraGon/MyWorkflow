package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.UserEntity;
import com.workflow.dto.UserDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T09:28:33+0100",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( entity.getId() );
        userDTO.setEmail( entity.getEmail() );
        userDTO.setUsername( entity.getUsername() );
        userDTO.setPassword( entity.getPassword() );

        return userDTO;
    }
}
