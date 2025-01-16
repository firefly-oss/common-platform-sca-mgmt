package com.catalis.core.sca.core.mappers;

import com.catalis.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import com.catalis.core.sca.models.entities.SCAOperationHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAOperationHistoryMapper {
    SCAOperationHistoryDTO toDTO(SCAOperationHistory entity);
    SCAOperationHistory toEntity(SCAOperationHistoryDTO dto);
}
