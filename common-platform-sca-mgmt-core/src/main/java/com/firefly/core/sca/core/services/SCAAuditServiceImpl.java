package com.firefly.core.sca.core.services;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.sca.core.mappers.SCAAuditMapper;
import com.firefly.core.sca.interfaces.dtos.SCAAuditDTO;
import com.firefly.core.sca.models.entities.SCAAudit;
import com.firefly.core.sca.models.repositories.SCAAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Transactional
public class SCAAuditServiceImpl implements SCAAuditService {

    @Autowired
    private SCAAuditRepository repository;

    @Autowired
    private SCAAuditMapper mapper;

    @Override
    public Mono<PaginationResponse<SCAAuditDTO>> findAllByOperationId(UUID operationId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllByScaOperationId(operationId, pageable),
                () -> repository.countByScaOperationId(operationId)
        );
    }

    @Override
    public Mono<SCAAuditDTO> create(UUID operationId, SCAAuditDTO dto) {
        SCAAudit entity = mapper.toEntity(dto);
        entity.setScaOperationId(operationId);
        return repository.save(entity).map(mapper::toDTO);
    }

    @Override
    public Mono<SCAAuditDTO> findById(UUID operationId, UUID auditId) {
        return repository.findById(auditId)
                .filter(audit -> audit.getScaOperationId().equals(operationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SCAAuditDTO> update(UUID operationId, UUID auditId, SCAAuditDTO dto) {
        return repository.findById(auditId)
                .filter(audit -> audit.getScaOperationId().equals(operationId))
                .flatMap(existingAudit -> {
                    SCAAudit updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setId(auditId);
                    updatedEntity.setScaOperationId(operationId);
                    return repository.save(updatedEntity);
                }).map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID operationId, UUID auditId) {
        return repository.findById(auditId)
                .filter(audit -> audit.getScaOperationId().equals(operationId))
                .flatMap(repository::delete);
    }
}
