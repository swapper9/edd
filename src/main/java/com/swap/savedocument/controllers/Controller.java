package com.swap.savedocument.controllers;

import com.swap.savedocument.dto.SaveDocumentResource;
import com.swap.savedocument.rest.assemblers.SaveDocAssembler;
import com.swap.savedocument.service.SaveDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    private SaveDocumentService saveDocumentService;

    @PostMapping(value = "/saveDocument")
    public UUID saveDocument(@RequestBody SaveDocumentResource resource) {
        return saveDocumentService.saveDocument(SaveDocAssembler.toSaveDocumentCommandFromDto(resource));
    }
}
