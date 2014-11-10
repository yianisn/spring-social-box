package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxUserMiniMixin {
    @JsonProperty("name")
    String name;
    @JsonProperty("login")
    String login;
}

