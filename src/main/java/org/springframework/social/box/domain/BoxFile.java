package org.springframework.social.box.domain;

import org.springframework.social.box.domain.internal.BoxFilePermissions;
import org.springframework.social.box.domain.internal.BoxFileSystemObject;

public class BoxFile extends BoxFileSystemObject {
    private String sha1;
    private String itemStatus;
    private String versionNumber;
    private Integer commentCount;
    private BoxFilePermissions permissions;
    private String extension;
    private Boolean isPackage;

    public String getSha1() {
        return sha1;
    }
    public String getItemStatus() {
        return itemStatus;
    }
    public String getVersionNumber() {
        return versionNumber;
    }
    public Integer getCommentCount() {
        return commentCount;
    }
    public BoxFilePermissions getPermissions() {
        return permissions;
    }
    public String getExtension() {
        return extension;
    }
    public Boolean getIsPackage() {
        return isPackage;
    }
};
