package org.springframework.social.box.domain.json;

import org.springframework.social.box.domain.internal.BoxFilePermissions;
import org.springframework.social.box.domain.internal.BoxPathCollection;
import org.springframework.social.box.domain.internal.json.BoxFileSystemObjectMixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFileMixin extends BoxFileSystemObjectMixin {
    @JsonProperty("sha1")
    String sha1;
    @JsonProperty("path_collection")
	BoxPathCollection pathCollection;
    @JsonProperty("item_status")
	String itemStatus;
    @JsonProperty("version_number")
	String versionNumber;
    @JsonProperty("comment_count")
	Integer commentCount;
    @JsonProperty("permissions")
	BoxFilePermissions permissions;
    @JsonProperty("extension")
	String extension;
    @JsonProperty("is_package")
	Boolean isPackage;
};
