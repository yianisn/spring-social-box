package org.springframework.social.box.domain;

import java.util.Date;
import java.util.List;

import org.springframework.social.box.domain.enums.BoxUserRole;
import org.springframework.social.box.domain.enums.BoxUserStatus;
import org.springframework.social.box.domain.internal.BoxEnterpriseMini;
import org.springframework.social.box.domain.internal.BoxIdentifiableObject;

public class BoxUser extends BoxIdentifiableObject {
    private String name;
    private String login;
    private Date createdAt;
    private Date modifiedAt;
    private BoxUserRole role;
    private String language;
    private String timezone;
    private Long spaceAmount;
    private Long spaceUsed;
    private Long maxUploadSize;
    List<String> trackingCodes;
    private Boolean canSeeManagedUsers;
    private Boolean isSyncEnabled;
    private BoxUserStatus status;
    private String jobTitle;
    private String phone;
    private String address;
    private String avatarUrl;
    private Boolean isExemptFromDeviceLimits;
    private Boolean isExemptFromLoginVerification;
    private BoxEnterpriseMini enterprise;
    List<String> myTags;

    public String getName() {
        return name;
    }
    public String getLogin() {
        return login;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getModifiedAt() {
        return modifiedAt;
    }
    public BoxUserRole getRole() {
        return role;
    }
    public String getLanguage() {
        return language;
    }
    public String getTimezone() {
        return timezone;
    }
    public Long getSpaceAmount() {
        return spaceAmount;
    }
    public Long getSpaceUsed() {
        return spaceUsed;
    }
    public Long getMaxUploadSize() {
        return maxUploadSize;
    }
    public List<String> getTrackingCodes() {
        return trackingCodes;
    }
    public Boolean getCanSeeManagedUsers() {
        return canSeeManagedUsers;
    }
    public Boolean getIsSyncEnabled() {
        return isSyncEnabled;
    }
    public BoxUserStatus getStatus() {
        return status;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public Boolean getIsExemptFromDeviceLimits() {
        return isExemptFromDeviceLimits;
    }
    public Boolean getIsExemptFromLoginVerification() {
        return isExemptFromLoginVerification;
    }
    public BoxEnterpriseMini getEnterprise() {
        return enterprise;
    }
    public List<String> getMyTags() {
        return myTags;
    }

}