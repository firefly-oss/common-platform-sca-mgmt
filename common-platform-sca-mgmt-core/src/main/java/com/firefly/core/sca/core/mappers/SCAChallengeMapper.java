package com.firefly.core.sca.core.mappers;

import com.firefly.core.sca.interfaces.dtos.SCAChallengeDTO;
import com.firefly.core.sca.models.entities.SCAChallenge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SCAChallengeMapper {
    SCAChallengeDTO toDTO(SCAChallenge entity);
    SCAChallenge toEntity(SCAChallengeDTO dto);
}
