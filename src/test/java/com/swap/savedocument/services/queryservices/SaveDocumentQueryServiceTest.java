package com.swap.savedocument.services.queryservices;

import com.swap.savedocument.domain.aggregates.Document;
import com.swap.savedocument.repository.SaveDocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SaveDocumentQueryService.class})
@ExtendWith(SpringExtension.class)
class SaveDocumentQueryServiceTest {
    @Autowired
    private SaveDocumentQueryService saveDocumentQueryService;

    @MockBean
    private SaveDocumentRepository saveDocumentRepository;

    @Test
    void testFindById() {
        Optional<Document> ofResult = Optional.of(new Document());
        when(this.saveDocumentRepository.findById(any())).thenReturn(ofResult);
        Optional<Document> actualFindByIdResult = this.saveDocumentQueryService.findById(UUID.randomUUID());
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.saveDocumentRepository).findById(any());
    }
}

