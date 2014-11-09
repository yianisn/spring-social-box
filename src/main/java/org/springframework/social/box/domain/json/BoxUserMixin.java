package org.springframework.social.box.domain.json;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.social.box.domain.enums.BoxUserRole;
import org.springframework.social.box.domain.enums.BoxUserStatus;
import org.springframework.social.box.domain.internal.BoxEnterpriseMini;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BoxUserMixin {
    @JsonProperty("name")
    String name;
    @JsonProperty("login")
    String login;
    @JsonProperty("created_at")
    Date createdAt;
    @JsonProperty("modified_at")
    Date modifiedAt;
    @JsonProperty("role")
    @JsonDeserialize(using=BoxUserRoleDeserializer.class)
    BoxUserRole role;
    @JsonProperty("language")
    String language;
    @JsonProperty("timezone")
    String timezone;
    @JsonProperty("space_amount")
    Long spaceAmount;
    @JsonProperty("space_used")
    Long spaceUsed;
    @JsonProperty("max_upload_size")
    Long maxUploadSize;
    @JsonProperty("tracking_codes")
    List<String> trackingCodes;
    @JsonProperty("can_see_managed_users")
    Boolean canSeeManagedUsers;
    @JsonProperty("is_sync_enabled")
    Boolean isSyncEnabled;
    @JsonProperty("status")
    @JsonDeserialize(using=BoxUserStatusDeserializer.class)
    BoxUserStatus status;
    @JsonProperty("job_title")
    String jobTitle;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("address")
    String address;
    @JsonProperty("avatar_url")
    String avatarUrl;
    @JsonProperty("is_exempt_from_device_limits")
    Boolean isExemptFromDeviceLimits;
    @JsonProperty("is_exempt_from_login_verification")
    Boolean isExemptFromLoginVerification;
    @JsonProperty("enterprise")
    BoxEnterpriseMini enterprise;
    @JsonProperty("my_tags")
    List<String> myTags;

    private static class BoxUserRoleDeserializer extends JsonDeserializer<BoxUserRole> {
        @Override
        public BoxUserRole deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return BoxUserRole.valueOf(jp.getText().toUpperCase());
        }
    }

    private static class BoxUserStatusDeserializer extends JsonDeserializer<BoxUserStatus> {
        @Override
        public BoxUserStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return BoxUserStatus.valueOf(jp.getText().toUpperCase());
        }
    }
}