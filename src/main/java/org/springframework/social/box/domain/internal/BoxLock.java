package org.springframework.social.box.domain.internal;

import java.util.Date;

public class BoxLock extends BoxIdentifiableObject {
	private BoxUserMini createdBy;
	private Date createdAt;
	private Date expiresAt;
	private Boolean isDownloadPrevented;

	public BoxUserMini getCreatedBy() {
		return createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getExpiresAt() {
		return expiresAt;
	}
	public Boolean getIsDownloadPrevented() {
		return isDownloadPrevented;
	}
}