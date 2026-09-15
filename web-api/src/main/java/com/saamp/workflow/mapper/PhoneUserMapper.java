package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.workflow.dto.PhoneContactDTO;
import com.workflow.dto.PhoneContactUpdateRequestDTO;
import com.workflow.dto.PhoneUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper MapStruct pour convertir les entités PhoneContact vers les DTO OpenAPI.
 *
 * Note:
 * - Les mises à jour sont volontairement gérées côté service (normalisation/trim/blank->null),
 *   afin de maîtriser précisément l'écriture en base.
 */
@Mapper(componentModel = "spring", uses = {PhoneContactMapper.class})
public interface PhoneUserMapper {

    PhoneUserDTO toDto(PhoneUserEntity entity);
}
