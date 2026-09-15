package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.UserEntity;
import com.workflow.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * Mapper MapStruct pour convertir les entités PhoneContact vers les DTO OpenAPI.
 * Note:
 * - Les mises à jour sont volontairement gérées côté service (normalisation/trim/blank->null),
 *   afin de maîtriser précisément l'écriture en base.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(UserEntity entity);
}
