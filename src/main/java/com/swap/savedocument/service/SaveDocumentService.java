package com.swap.savedocument.service;

import com.swap.savedocument.domain.aggregates.Document;
import com.swap.savedocument.domain.commands.SaveDocumentCommand;
import com.swap.savedocument.repository.SaveDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaveDocumentService {

    private final SaveDocumentRepository saveDocumentRepository;

    public SaveDocumentService(SaveDocumentRepository saveDocumentRepository) {
        this.saveDocumentRepository = saveDocumentRepository;
    }

    public UUID saveDocument(SaveDocumentCommand cmd) {
        UUID uuid = UUID.randomUUID();
        cmd.setId(uuid);
        Document document = new Document(cmd);
        saveDocumentRepository.save(document);
        return uuid;
    }
}
