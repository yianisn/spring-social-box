package org.springframework.social.box.domain.internal.json;

import java.util.List;

import org.springframework.social.box.domain.internal.BoxFolderMini;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxPathCollectionMixin {
    @JsonProperty("total_count")
    Integer totalCount;
    @JsonProperty("entries")
    List<BoxFolderMini> entries;
}