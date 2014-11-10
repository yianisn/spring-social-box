package org.springframework.social.box.domain.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFolderPermissionsMixin {
    @JsonProperty("can_invite_collaborator")
    Boolean canInviteCollaborator;
}