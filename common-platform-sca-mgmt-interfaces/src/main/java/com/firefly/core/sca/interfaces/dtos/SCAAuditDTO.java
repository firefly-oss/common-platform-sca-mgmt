package com.firefly.core.sca.interfaces.dtos;

import com.firefly.core.sca.interfaces.enums.SCAEventTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for storing deeper audit logs for the SCA process.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SCAAuditDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @FilterableId
    private UUID scaOperationId;

    @FilterableId
    private UUID scaChallengeId;

    @FilterableId
    private String partyId;

    private SCAEventTypeEnum eventType;
    private LocalDateTime eventTime;
    private String details;
}

