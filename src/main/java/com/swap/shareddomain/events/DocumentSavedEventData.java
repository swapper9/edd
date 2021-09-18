package com.swap.shareddomain.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DocumentSavedEventData {
    private UUID id;
}
