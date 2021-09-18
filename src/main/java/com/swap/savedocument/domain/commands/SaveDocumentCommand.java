package com.swap.savedocument.domain.commands;

import lombok.Data;

import java.util.UUID;

@Data
public class SaveDocumentCommand {
    private UUID id;
    private Long documentId;
    private String documentData;

    public SaveDocumentCommand(Long documentId, String documentData) {
        this.documentId = documentId;
        this.documentData = documentData;
    }
}
