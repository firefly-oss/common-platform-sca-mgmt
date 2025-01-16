package com.catalis.core.sca.core.mappers;

import com.catalis.core.sca.interfaces.dtos.SCAChallengeDTO;
import com.catalis.core.sca.models.entities.SCAChallenge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAChallengeMapper {
    SCAChallengeDTO toDTO(SCAChallenge entity);
    SCAChallenge toEntity(SCAChallengeDTO dto);
}
