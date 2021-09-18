package com.swap.savedocument.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.swap.savedocument.domain.aggregates.Document;
import com.swap.savedocument.dto.SaveDocumentResource;
import com.swap.savedocument.repository.SaveDocumentRepository;
import com.swap.savedocument.services.commandservices.SaveDocumentCommandService;
import com.swap.savedocument.services.queryservices.SaveDocumentQueryService;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Controller.class})
@ExtendWith(SpringExtension.class)
class ControllerTest {
    @Autowired
    private Controller controller;

    @MockBean
    private SaveDocumentCommandService saveDocumentCommandService;

    @MockBean
    private SaveDocumentQueryService saveDocumentQueryService;

    @Test
    void testSaveDocument() {
        SaveDocumentRepository saveDocumentRepository = mock(SaveDocumentRepository.class);
        when(saveDocumentRepository.save(any())).thenReturn(new Document());
        SaveDocumentCommandService saveDocumentCommandService = new SaveDocumentCommandService(saveDocumentRepository);
        Controller controller = new Controller(saveDocumentCommandService,
                new SaveDocumentQueryService(mock(SaveDocumentRepository.class)));

        SaveDocumentResource saveDocumentResource = new SaveDocumentResource();
        saveDocumentResource.setId(123L);
        saveDocumentResource.setDocumentData("Document Data");
        controller.saveDocument(saveDocumentResource);
        verify(saveDocumentRepository).save(any());
    }

    @Test
    void testGetDocument() throws Exception {
        when(this.saveDocumentQueryService.findById(any())).thenReturn(Optional.of(new Document()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/document/{uuid}", UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"doc\":null}"));
    }
}

