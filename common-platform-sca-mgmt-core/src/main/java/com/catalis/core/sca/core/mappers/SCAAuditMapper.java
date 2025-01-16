package com.catalis.core.sca.core.mappers;

import com.catalis.core.sca.interfaces.dtos.SCAAuditDTO;
import com.catalis.core.sca.models.entities.SCAAudit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAAuditMapper {
    SCAAuditDTO toDTO(SCAAudit entity);
    SCAAudit toEntity(SCAAuditDTO dto);
}
