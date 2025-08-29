package com.firefly.core.sca.core.mappers;

import com.firefly.core.sca.interfaces.dtos.SCAAuditDTO;
import com.firefly.core.sca.models.entities.SCAAudit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAAuditMapper {
    SCAAuditDTO toDTO(SCAAudit entity);
    SCAAudit toEntity(SCAAuditDTO dto);
}
