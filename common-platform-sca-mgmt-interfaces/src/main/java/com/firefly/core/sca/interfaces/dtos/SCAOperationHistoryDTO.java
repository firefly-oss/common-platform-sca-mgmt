package com.firefly.core.sca.interfaces.dtos;

import com.firefly.core.sca.interfaces.enums.SCAStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing a record of changes or events for an SCA operation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SCAOperationHistoryDTO {
    private UUID id;
    private UUID scaOperationId;
    private SCAStatusEnum status;
    private LocalDateTime eventTime;
    private String comments;
}
