package org.springframework.social.box.domain.json;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.social.box.domain.enums.BoxUserRole;
import org.springframework.social.box.domain.enums.BoxUserStatus;
import org.springframework.social.box.domain.internal.BoxEnterpriseMini;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BoxUserMixin {
    @JsonProperty("name")
    private String name;
    @JsonProperty("login")
    private String login;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("modified_at")
    private Date modifiedAt;
    @JsonProperty("role")
    @JsonDeserialize(using=BoxUserRoleDeserializer.class)
    private BoxUserRole role;
    @JsonProperty("language")
    private String language;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("space_amount")
    private Long spaceAmount;
    @JsonProperty("space_used")
    private Long spaceUsed;
    @JsonProperty("max_upload_size")
    private Long maxUploadSize;
    @JsonProperty("tracking_codes")
    List<String> trackingCodes;
    @JsonProperty("can_see_managed_users")
    private Boolean canSeeManagedUsers;
    @JsonProperty("is_sync_enabled")
    private Boolean isSyncEnabled;
    @JsonProperty("status")
    @JsonDeserialize(using=BoxUserStatusDeserializer.class)
    private BoxUserStatus status;
    @JsonProperty("job_title")
    private String jobTitle;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("address")
    private String address;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("is_exempt_from_device_limits")
    private Boolean isExemptFromDeviceLimits;
    @JsonProperty("is_exempt_from_login_verification")
    private Boolean isExemptFromLoginVerification;
    @JsonProperty("enterprise")
    private BoxEnterpriseMini enterprise;
    @JsonProperty("my_tags")
    List<String> myTags;

    private static class BoxUserRoleDeserializer extends JsonDeserializer<BoxUserRole> {
        @Override
        public BoxUserRole deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return BoxUserRole.valueOf(jp.getText().toUpperCase());
        }
    }

    private static class BoxUserStatusDeserializer extends JsonDeserializer<BoxUserStatus> {
        @Override
        public BoxUserStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return BoxUserStatus.valueOf(jp.getText().toUpperCase());
        }
    }
}