package com.catalis.core.sca.models.entities;

import com.catalis.core.sca.interfaces.enums.SCAStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Stores a record of status changes or events for each SCA operation.
 */
@Data
@Table("sca_operation_history")
public class SCAOperationHistory {

    @Id
    @Column("id")
    private Long id;

    @Column("sca_operation_id")
    private Long scaOperationId;

    @Column("status")
    private SCAStatusEnum status;

    @Column("event_time")
    private LocalDateTime eventTime;

    @Column("comments")
    private String comments;
}

