package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFileMiniMixin extends BoxItemObjectMixin {
    @JsonProperty("sha1")
    String sha1;
}

