package com.saamp.workflow.mapper;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.workflow.dto.PhoneContactDTO;
import com.workflow.dto.PhoneContactUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper MapStruct pour convertir les entités PhoneContact vers les DTO OpenAPI.
 *
 * Note:
 * - Les mises à jour sont volontairement gérées côté service (normalisation/trim/blank->null),
 *   afin de maîtriser précisément l'écriture en base.
 */
@Mapper(componentModel = "spring")
public interface PhoneContactMapper {

    PhoneContactDTO toDto(PhoneContactEntity entity);

    /**
     * Applique les champs modifiables sur une entité existante.
     * Par défaut MapStruct propage les nulls : permet de "vider" un champ (ex: company=null).
     */
    void applyUpdate(PhoneContactUpdateRequestDTO request, @MappingTarget PhoneContactEntity entity);
}
