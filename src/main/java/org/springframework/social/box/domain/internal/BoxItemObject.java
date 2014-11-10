package org.springframework.social.box.domain.internal;

public abstract class BoxItemObject extends BoxIdentifiableObject {
    private String sequenceId;
    private String etag;
    private String name;

    public String getSequenceId() {
        return sequenceId;
    }
    public String getEtag() {
        return etag;
    }
    public String getName() {
        return name;
    }
}

