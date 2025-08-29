package com.firefly.core.sca.core.mappers;

import com.firefly.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import com.firefly.core.sca.models.entities.SCAOperationHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAOperationHistoryMapper {
    SCAOperationHistoryDTO toDTO(SCAOperationHistory entity);
    SCAOperationHistory toEntity(SCAOperationHistoryDTO dto);
}
