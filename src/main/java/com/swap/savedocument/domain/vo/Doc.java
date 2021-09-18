package com.swap.savedocument.domain.vo;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Doc {
    private String documentData;

    public Doc(String documentData) {
        this.documentData = documentData;
    }

    public String getDocumentData() {
        return documentData;
    }

    public void setDocumentData(String documentData) {
        this.documentData = documentData;
    }
}
