package com.swap.savedocument.services.queryservices;

import com.swap.savedocument.domain.aggregates.Document;
import com.swap.savedocument.repository.SaveDocumentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class SaveDocumentQueryService {

    private final SaveDocumentRepository repository;

    public SaveDocumentQueryService(SaveDocumentRepository repository) {
        this.repository = repository;
    }

    public Mono<Optional<Document>> findById(UUID id) {
        return Mono.just(repository.findById(id));
    }
}
