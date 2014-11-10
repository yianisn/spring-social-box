package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFolderUploadEmailMixin {
    @JsonProperty("access")
    String access;
    @JsonProperty("email")
    String email;
}

