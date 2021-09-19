package com.swap.savedocument.services.commandservices;

import com.swap.savedocument.domain.aggregates.Document;
import com.swap.savedocument.domain.commands.SaveDocumentCommand;
import com.swap.savedocument.repository.SaveDocumentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class SaveDocumentCommandService {

    private final SaveDocumentRepository saveDocumentRepository;

    public SaveDocumentCommandService(SaveDocumentRepository saveDocumentRepository) {
        this.saveDocumentRepository = saveDocumentRepository;
    }

    public Mono<UUID> saveDocument(SaveDocumentCommand cmd) {
        UUID uuid = UUID.randomUUID();
        cmd.setId(uuid);
        Document document = new Document(cmd);
        saveDocumentRepository.save(document);
        return Mono.just(uuid);
    }
}
