package com.firefly.core.sca.core.mappers;

import com.firefly.core.sca.interfaces.dtos.SCAAttemptDTO;
import com.firefly.core.sca.models.entities.SCAAttempt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAAttemptMapper {
    SCAAttemptDTO toDTO(SCAAttempt entity);
    SCAAttempt toEntity(SCAAttemptDTO dto);
}