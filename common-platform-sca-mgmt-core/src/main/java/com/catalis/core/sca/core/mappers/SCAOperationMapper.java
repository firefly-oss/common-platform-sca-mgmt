package com.catalis.core.sca.core.mappers;

import com.catalis.core.sca.interfaces.dtos.SCAOperationDTO;
import com.catalis.core.sca.models.entities.SCAOperation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAOperationMapper {
    SCAOperationDTO toDTO(SCAOperation operation);
    SCAOperation toEntity(SCAOperationDTO dto);
}