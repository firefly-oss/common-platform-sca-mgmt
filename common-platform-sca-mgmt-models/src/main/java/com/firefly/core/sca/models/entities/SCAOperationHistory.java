package com.firefly.core.sca.models.entities;

import com.firefly.core.sca.interfaces.enums.SCAStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Stores a record of status changes or events for each SCA operation.
 */
@Data
@Table("sca_operation_history")
public class SCAOperationHistory {

    @Id
    @Column("id")
    private UUID id;

    @Column("sca_operation_id")
    private UUID scaOperationId;

    @Column("status")
    private SCAStatusEnum status;

    @Column("event_time")
    private LocalDateTime eventTime;

    @Column("comments")
    private String comments;
}

