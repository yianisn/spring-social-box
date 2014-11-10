package org.springframework.social.box.domain.internal.json;

import java.util.Date;

import org.springframework.social.box.domain.internal.BoxUserMini;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxLockMixin {
    @JsonProperty("created_by")
    BoxUserMini createdBy;
    @JsonProperty("created_at")
    Date createdAt;
    @JsonProperty("expires_at")
    Date expiresAt;
    @JsonProperty("is_download_prevented")
    Boolean isDownloadPrevented;
}