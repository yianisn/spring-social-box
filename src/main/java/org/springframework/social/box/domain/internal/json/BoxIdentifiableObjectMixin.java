package org.springframework.social.box.domain.internal.json;

import java.io.IOException;

import org.springframework.social.box.domain.enums.BoxItemType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxIdentifiableObjectMixin {
    @JsonProperty("id")
    String id;
    @JsonProperty("type")
    @JsonDeserialize(using=BoxItemTypeDeserializer.class)
	BoxItemType type;

    public static class BoxItemTypeDeserializer extends JsonDeserializer<BoxItemType> {
        @Override
        public BoxItemType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return BoxItemType.valueOf(jp.getText().toUpperCase());
        }
    }
}
