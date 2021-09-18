package com.swap.savedocument.rest.assemblers;

import com.swap.savedocument.domain.commands.SaveDocumentCommand;
import com.swap.savedocument.dto.SaveDocumentResource;

public class SaveDocAssembler {

    public static SaveDocumentCommand toSaveDocumentCommandFromDto(SaveDocumentResource resource) {
        return new SaveDocumentCommand(resource.getId(), resource.getDocumentData());
    }
}
