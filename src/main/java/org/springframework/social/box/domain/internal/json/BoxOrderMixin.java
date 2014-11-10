package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxOrderMixin {
    @JsonProperty("by")
    String by;
    @JsonProperty("direction")
    String direction;
}