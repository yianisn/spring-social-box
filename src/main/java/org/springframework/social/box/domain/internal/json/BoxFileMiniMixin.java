package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFileMiniMixin {
    @JsonProperty("sha1")
	private String sha1;
}

