package com.catalis.core.sca.models.entities;

import com.catalis.core.sca.interfaces.enums.SCAEventTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Logs deeper events or metadata for compliance/audit.
 */
@Data
@Table("sca_audit")
public class SCAAudit {

    @Id
    @Column("id")
    private Long id;

    @Column("sca_operation_id")
    private Long scaOperationId; // references sca_operation.id

    @Column("sca_challenge_id")
    private Long scaChallengeId; // references sca_challenge.id (optional)

    @Column("party_id")
    private String partyId; // same as sca_operation, if relevant

    @Column("event_type")
    private SCAEventTypeEnum eventType; // e.g. "CREATED", "ATTEMPTED", "VERIFIED"

    @Column("event_time")
    private LocalDateTime eventTime;

    @Column("details")
    private String details; // metadata or JSON with extra info
}