package com.catalis.core.sca.core.services;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.sca.core.mappers.SCAOperationHistoryMapper;
import com.catalis.core.sca.interfaces.dtos.SCAOperationHistoryDTO;
import com.catalis.core.sca.models.entities.SCAOperationHistory;
import com.catalis.core.sca.models.repositories.SCAOperationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SCAOperationHistoryServiceImpl implements SCAOperationHistoryService {

    @Autowired
    private SCAOperationHistoryRepository repository;

    @Autowired
    private SCAOperationHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<SCAOperationHistoryDTO>> findAllByOperationId(Long operationId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllByScaOperationId(operationId, pageable),
                () -> repository.countByScaOperationId(operationId)
        );
    }

    @Override
    public Mono<SCAOperationHistoryDTO> create(Long operationId, SCAOperationHistoryDTO dto) {
        SCAOperationHistory entity = mapper.toEntity(dto);
        entity.setScaOperationId(operationId);
        return repository.save(entity).map(mapper::toDTO);
    }

    @Override
    public Mono<SCAOperationHistoryDTO> findById(Long operationId, Long historyId) {
        return repository.findById(historyId)
                .filter(history -> history.getScaOperationId().equals(operationId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SCAOperationHistoryDTO> update(Long operationId, Long historyId, SCAOperationHistoryDTO dto) {
        return repository.findById(historyId)
                .filter(history -> history.getScaOperationId().equals(operationId))
                .flatMap(existingHistory -> {
                    SCAOperationHistory updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setId(historyId);
                    updatedEntity.setScaOperationId(operationId);
                    return repository.save(updatedEntity);
                }).map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long operationId, Long historyId) {
        return repository.findById(historyId)
                .filter(history -> history.getScaOperationId().equals(operationId))
                .flatMap(repository::delete);
    }
}