package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFilePermissionsMixin {
    @JsonProperty("can_preview")
    Boolean canPreview;
    @JsonProperty("can_comment")
    Boolean canComment;
}