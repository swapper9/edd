package com.swap.savedocument.domain.aggregates;

import com.swap.savedocument.domain.commands.SaveDocumentCommand;
import com.swap.savedocument.domain.vo.Doc;
import com.swap.shareddomain.events.DocumentSavedEvent;
import com.swap.shareddomain.events.DocumentSavedEventData;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Document extends AbstractAggregateRoot<Document> {
    @Id
    private UUID id;

    @Embedded
    private Doc doc;

    public Document(SaveDocumentCommand cmd) {
        this.id = cmd.getId();
        this.doc = new Doc(cmd.getDocumentData());

        addDomainEvent(new DocumentSavedEvent(new DocumentSavedEventData(cmd.getId())));
    }

    public UUID getId() {
        return id;
    }

    public Doc getDoc() {
        return doc;
    }


    public void addDomainEvent(Object event) {
        registerEvent(event);
    }
}
