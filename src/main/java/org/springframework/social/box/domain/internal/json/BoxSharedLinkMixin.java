package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxSharedLinkMixin {
    @JsonProperty("url")
	String url;
    @JsonProperty("download_url")
	String downloadUrl;
    @JsonProperty("vanity_url")
	String vanityUrl;
    @JsonProperty("effective_access")
	String effectiveAccess;
    @JsonProperty("is_password_enabled")
	String isPasswordEnabled;
    @JsonProperty("unshared_at")
	String unsharedAt;
    @JsonProperty("download_count")
	String downloadCount;
    @JsonProperty("preview_count")
	String previewCount;
    @JsonProperty("access")
	String access;
    @JsonProperty("permissions")
	BoxFilePermissionsMixin permissions;
}