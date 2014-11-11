package org.springframework.social.box.domain.json;

import org.springframework.social.box.domain.enums.BoxItemType;
import org.springframework.social.box.domain.internal.json.BoxIdentifiableObjectMixin.BoxItemTypeDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BoxErrorMixin {
    @JsonProperty("type")
    @JsonDeserialize(using=BoxItemTypeDeserializer.class)
    BoxItemType type;
    @JsonProperty("status")
    Integer status;
    @JsonProperty("code")
    String code;
    @JsonProperty("help_url")
    String helpUrl;
    @JsonProperty("message")
    String message;
    @JsonProperty("request_id")
    String requestId;
    @JsonProperty("context_info")
    JsonNode contextInfo;
}