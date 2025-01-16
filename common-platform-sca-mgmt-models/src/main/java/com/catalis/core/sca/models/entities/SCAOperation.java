package com.catalis.core.sca.models.entities;

import com.catalis.core.sca.interfaces.enums.SCAOperationTypeEnum;
import com.catalis.core.sca.interfaces.enums.SCAStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Represents the core SCA operation or "session" requiring strong customer auth.
 */
@Data
@Table("sca_operation")
public class SCAOperation {

    @Id
    @Column("id")
    private Long id;

    @Column("reference_id")
    private String referenceId;

    @Column("operation_type")
    private SCAOperationTypeEnum operationType;

    @Column("party_id")
    private String partyId;

    @Column("status")
    private SCAStatusEnum status;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("expires_at")
    private LocalDateTime expiresAt;

    @Column("last_updated")
    private LocalDateTime lastUpdated;

    @Column("cancelled_at")
    private LocalDateTime cancelledAt;
}
