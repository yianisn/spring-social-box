package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxEnterpriseMiniMixin {
    @JsonProperty("name")
    String name;
    @JsonProperty("login")
    String login;
}

