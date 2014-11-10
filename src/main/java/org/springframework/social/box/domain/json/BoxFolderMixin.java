package org.springframework.social.box.domain.json;

import java.io.IOException;

import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.social.box.domain.enums.BoxSyncState;
import org.springframework.social.box.domain.internal.json.BoxFolderPermissionsMixin;
import org.springframework.social.box.domain.internal.json.BoxFolderUploadEmailMixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BoxFolderMixin {
    @JsonProperty("folder_upload_email")
	BoxFolderUploadEmailMixin folderUploadEmail;
    @JsonProperty("item_status")
	String itemStatus;
    @JsonProperty("item_collection")
	BoxFolderItems itemCollection;
    @JsonProperty("sync_state")
    @JsonDeserialize(using=BoxSyncStateDeserializer.class)
	BoxSyncState syncState;
    @JsonProperty("has_collaborations")
	Boolean hasCollaborations;
    @JsonProperty("permissions")
	BoxFolderPermissionsMixin permissions;

    private static class BoxSyncStateDeserializer extends JsonDeserializer<BoxSyncState> {
        @Override
        public BoxSyncState deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return BoxSyncState.valueOf(jp.getText().toUpperCase());
        }
    }
};
