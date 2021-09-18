package com.swap.savedocument.services.commandservices;

import com.swap.savedocument.domain.aggregates.Document;
import com.swap.savedocument.domain.commands.SaveDocumentCommand;
import com.swap.savedocument.repository.SaveDocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SaveDocumentCommandService.class})
@ExtendWith(SpringExtension.class)
class SaveDocumentCommandServiceTest {
    @Autowired
    private SaveDocumentCommandService saveDocumentCommandService;

    @MockBean
    private SaveDocumentRepository saveDocumentRepository;

    @Test
    void testSaveDocument() {
        when(this.saveDocumentRepository.save(any(Document.class))).thenReturn(new Document());
        SaveDocumentCommand saveDocumentCommand = new SaveDocumentCommand(123L, "Document Data");

        UUID actualSaveDocumentResult = this.saveDocumentCommandService.saveDocument(saveDocumentCommand);
        verify(this.saveDocumentRepository).save(any(Document.class));
        assertSame(actualSaveDocumentResult, saveDocumentCommand.getId());
    }
}

