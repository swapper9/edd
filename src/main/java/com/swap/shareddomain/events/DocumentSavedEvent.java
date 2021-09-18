package com.swap.shareddomain.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentSavedEvent {
    private DocumentSavedEventData documentSavedEventData;
}
