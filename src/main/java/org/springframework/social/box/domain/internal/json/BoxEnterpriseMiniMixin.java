package org.springframework.social.box.domain.internal.json;

import org.springframework.social.box.domain.internal.BoxObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxEnterpriseMiniMixin {
    @JsonProperty("name")
    String name;
    @JsonProperty("login")
    String login;
}

