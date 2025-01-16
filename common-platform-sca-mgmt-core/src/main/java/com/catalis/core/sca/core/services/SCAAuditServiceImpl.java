package com.catalis.core.sca.core.services;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.sca.core.mappers.SCAAuditMapper;
import com.catalis.core.sca.interfaces.dtos.SCAAuditDTO;
import com.catalis.core.sca.models.entities.SCAAudit;
import com.catalis.core.sca.models.repositories.SCAAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SCAAuditServiceImpl implements SCAAuditService {

    @Autowired
    private SCAAuditRepository repository;

    @Autowired
    private SCAAuditMapper mapper;

    @Override
    public Mono<PaginationResponse<SCAAuditDTO>> findAllByOperationId(Long operationId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllByScaOperationId(operationId, pageable),
                () -> repository.countByScaOperationId(operationId)
        );
    }

    @Override
    public Mono<SCAAuditDTO> create(Long operationId, SCAAuditDTO dto) {
        SCAAudit entity = mapper.toEntity(dto);
        entity.setScaOperationId(operationId);
        return repository.save(entity).map(mapper::toDTO);
    }

    @Override
    public Mono<SCAAuditDTO> findById(Long operationId, Long auditId) {
        return repository.findById(auditId)
                .filter(audit -> audit.getScaOperationId().equals(operationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SCAAuditDTO> update(Long operationId, Long auditId, SCAAuditDTO dto) {
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
    public Mono<Void> delete(Long operationId, Long auditId) {
        return repository.findById(auditId)
                .filter(audit -> audit.getScaOperationId().equals(operationId))
                .flatMap(repository::delete);
    }
}
