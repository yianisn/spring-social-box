package org.springframework.social.box.domain.internal;

public class BoxFilePermissions extends BoxPermissionsObject {
	private Boolean canPreview;
	private Boolean canComment;

	public Boolean getCanPreview() {
		return canPreview;
	}
	public Boolean getCanComment() {
		return canComment;
	}
}