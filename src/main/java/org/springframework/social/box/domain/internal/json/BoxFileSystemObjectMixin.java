package org.springframework.social.box.domain.internal.json;

import java.util.Date;
import java.util.List;

import org.springframework.social.box.domain.internal.BoxFolderMini;
import org.springframework.social.box.domain.internal.BoxLock;
import org.springframework.social.box.domain.internal.BoxPathCollection;
import org.springframework.social.box.domain.internal.BoxSharedLink;
import org.springframework.social.box.domain.internal.BoxUserMini;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BoxFileSystemObjectMixin {
    @JsonProperty("description")
	String description;
    @JsonProperty("size")
	Long size;
    @JsonProperty("created_at")
    Date createdAt;
    @JsonProperty("modified_at")
    Date modifiedAt;
    @JsonProperty("trashed_at")
    Date trashedAt;
    @JsonProperty("purged_at")
    Date purgedAt;
    @JsonProperty("content_created_at")
    Date contentCreatedAt;
    @JsonProperty("content_modified_at")
    Date contentModifiedAt;
    @JsonProperty("path_collection")
	BoxPathCollection pathCollection;
    @JsonProperty("created_by")
    BoxUserMini createdBy;
    @JsonProperty("modified_by")
    BoxUserMini modifiedBy;
    @JsonProperty("owned_by")
    BoxUserMini ownedBy;
    @JsonProperty("shared_link")
	BoxSharedLink sharedLink;
    @JsonProperty("parent")
	BoxFolderMini parent;
    @JsonProperty("tags")
	List<String> tags;
    @JsonProperty("lock")
	BoxLock lock;
}
