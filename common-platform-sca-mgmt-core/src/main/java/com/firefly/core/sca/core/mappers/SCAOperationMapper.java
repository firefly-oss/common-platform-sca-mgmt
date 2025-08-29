package com.firefly.core.sca.core.mappers;

import com.firefly.core.sca.interfaces.dtos.SCAOperationDTO;
import com.firefly.core.sca.models.entities.SCAOperation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAOperationMapper {
    SCAOperationDTO toDTO(SCAOperation operation);
    SCAOperation toEntity(SCAOperationDTO dto);
}