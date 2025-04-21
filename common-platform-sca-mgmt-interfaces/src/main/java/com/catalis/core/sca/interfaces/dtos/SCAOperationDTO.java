package com.catalis.core.sca.interfaces.dtos;

import com.catalis.core.sca.interfaces.enums.SCAOperationTypeEnum;
import com.catalis.core.sca.interfaces.enums.SCAStatusEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO representing the core SCA Operation data exchanged over APIs.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SCAOperationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @FilterableId
    private String referenceId;

    private SCAOperationTypeEnum operationType;

    @FilterableId
    private String partyId;

    private SCAStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime lastUpdated;
    private LocalDateTime cancelledAt;
}

