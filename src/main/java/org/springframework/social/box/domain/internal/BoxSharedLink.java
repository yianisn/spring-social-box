package org.springframework.social.box.domain.internal;

public class BoxSharedLink extends BoxObject {
    private String url;
    private String downloadUrl;
    private String vanityUrl;
    private String effectiveAccess;
    private String isPasswordEnabled;
    private String unsharedAt;
    private String downloadCount;
    private String previewCount;
    private String access;
    private BoxFilePermissions permissions;

    public String getUrl() {
        return url;
    }
    public String getDownloadrl() {
        return downloadUrl;
    }
    public String getVanityUrl() {
        return vanityUrl;
    }
    public String getEffectiveAccess() {
        return effectiveAccess;
    }
    public String getIsPasswordEnabled() {
        return isPasswordEnabled;
    }
    public String getUnsharedAt() {
        return unsharedAt;
    }
    public String getDownloadCount() {
        return downloadCount;
    }
    public String getPreviewCount() {
        return previewCount;
    }
    public String getAccess() {
        return access;
    }
    public BoxFilePermissions getPermissions() {
        return permissions;
    }
}