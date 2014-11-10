package org.springframework.social.box.domain.internal;

import java.util.Date;
import java.util.List;

public abstract class BoxFileSystemObject extends BoxItemObject {
	private String description;
	private Long size;
    private Date createdAt;
    private Date modifiedAt;
    private Date trashedAt;
    private Date purgedAt;
    private Date contentCreatedAt;
    private Date contentModifiedAt;
	private BoxPathCollection pathCollection;
    private BoxUserMini createdBy;
    private BoxUserMini modifiedBy;
    private BoxUserMini ownedBy;
	private BoxSharedLink sharedLink;
	private BoxFolderMini parent;
	private List<String> tags;
	private BoxLock lock;

	public String getDescription() {
		return description;
	}
	public Long getSize() {
		return size;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public Date getTrashedAt() {
		return trashedAt;
	}
	public Date getPurgedAt() {
		return purgedAt;
	}
	public Date getContentCreatedAt() {
		return contentCreatedAt;
	}
	public Date getContentModifiedAt() {
		return contentModifiedAt;
	}
	public BoxPathCollection getPathCollection() {
		return pathCollection;
	}
	public BoxUserMini getCreatedBy() {
		return createdBy;
	}
	public BoxUserMini getModifiedBy() {
		return modifiedBy;
	}
	public BoxUserMini getOwnedBy() {
		return ownedBy;
	}
	public BoxSharedLink getSharedLink() {
		return sharedLink;
	}
	public BoxFolderMini getParent() {
		return parent;
	}
	public List<String> getTags() {
		return tags;
	}
	public BoxLock getLock() {
		return lock;
	}
}
