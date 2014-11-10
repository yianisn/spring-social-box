package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxPermissionsObjectMixin {
	@JsonProperty("can_download")
    Boolean canDownload;
	@JsonProperty("can_upload")
	Boolean canUpload;
	@JsonProperty("can_rename")
	Boolean canRename;
	@JsonProperty("can_delete")
	Boolean canDelete;
	@JsonProperty("can_share")
	Boolean canShare;
	@JsonProperty("can_set_share_access")
	Boolean canSetShareAccess;
}