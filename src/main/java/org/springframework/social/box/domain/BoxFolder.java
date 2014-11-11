package org.springframework.social.box.domain;

import org.springframework.social.box.domain.enums.BoxSyncState;
import org.springframework.social.box.domain.internal.BoxFileSystemObject;
import org.springframework.social.box.domain.internal.json.BoxFolderPermissionsMixin;
import org.springframework.social.box.domain.internal.json.BoxFolderUploadEmailMixin;

public class BoxFolder extends BoxFileSystemObject {
    private BoxFolderUploadEmailMixin folderUploadEmail;
    private String itemStatus;
    private BoxFolderItems itemCollection;
    private BoxSyncState syncState;
    private Boolean hasCollaborations;
    private BoxFolderPermissionsMixin permissions;

    public BoxFolderUploadEmailMixin getFolderUploadEmail() {
        return folderUploadEmail;
    }
    public String getItemStatus() {
        return itemStatus;
    }
    public BoxFolderItems getItemCollection() {
        return itemCollection;
    }
    public BoxSyncState getSyncState() {
        return syncState;
    }
    public Boolean getHasCollaborations() {
        return hasCollaborations;
    }
    public BoxFolderPermissionsMixin getPermissions() {
        return permissions;
    }
};
