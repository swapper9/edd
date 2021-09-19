package com.swap.savedocument.controllers;

import com.swap.savedocument.domain.aggregates.Document;
import com.swap.savedocument.dto.SaveDocumentResource;
import com.swap.savedocument.rest.assemblers.SaveDocAssembler;
import com.swap.savedocument.services.commandservices.SaveDocumentCommandService;
import com.swap.savedocument.services.queryservices.SaveDocumentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@RestController
public class Controller {

    private final SaveDocumentCommandService saveDocumentCommandService;
    private final SaveDocumentQueryService saveDocumentQueryService;

    public Controller(SaveDocumentCommandService saveDocumentCommandService, SaveDocumentQueryService saveDocumentQueryService) {
        this.saveDocumentCommandService = saveDocumentCommandService;
        this.saveDocumentQueryService = saveDocumentQueryService;
    }

    @PostMapping(value = "/document")
    public Mono<UUID> saveDocument(@RequestBody SaveDocumentResource resource) {
        return saveDocumentCommandService.saveDocument(SaveDocAssembler.toSaveDocumentCommandFromDto(resource));
    }

    @GetMapping(value = "/document/{uuid}")
    public Mono<ResponseEntity<Optional<Document>>> getDocument(@PathVariable UUID uuid) {
        return saveDocumentQueryService.findById(uuid)
                .map(document -> new ResponseEntity<>(document, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
