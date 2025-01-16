package com.catalis.core.sca.core.mappers;

import com.catalis.core.sca.interfaces.dtos.SCAAttemptDTO;
import com.catalis.core.sca.models.entities.SCAAttempt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAAttemptMapper {
    SCAAttemptDTO toDTO(SCAAttempt entity);
    SCAAttempt toEntity(SCAAttemptDTO dto);
}