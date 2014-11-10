package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BoxItemObjectMixin extends BoxIdentifiableObjectMixin {
    @JsonProperty("sequence_id")
	String sequenceId;
    @JsonProperty("etag")
	String etag;
    @JsonProperty("name")
	String name;
}

