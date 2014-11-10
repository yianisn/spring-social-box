package org.springframework.social.box.domain.internal;

public class BoxPermissionsObject extends BoxObject {
	private Boolean canDownload;
	private Boolean canUpload;
	private Boolean canRename;
	private Boolean canDelete;
	private Boolean canShare;
	private Boolean canSetShareAccess;

	public Boolean getCanDownload() {
		return canDownload;
	}
	public Boolean getCan_Upload() {
		return canUpload;
	}
	public Boolean getCanRename() {
		return canRename;
	}
	public Boolean getCanDelete() {
		return canDelete;
	}
	public Boolean getCanShare() {
		return canShare;
	}
	public Boolean getCanSetShareAccess() {
		return canSetShareAccess;
	}
}