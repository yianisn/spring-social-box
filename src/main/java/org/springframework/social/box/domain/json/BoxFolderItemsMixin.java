package org.springframework.social.box.domain.json;

import java.util.List;

import org.springframework.social.box.domain.internal.json.BoxFileMiniMixin;
import org.springframework.social.box.domain.internal.json.BoxOrderMixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFolderItemsMixin {
    @JsonProperty("total_count")
    Integer totalCount;
    @JsonProperty("offset")
    Integer offset;
    @JsonProperty("limit")
    Integer limit;
    @JsonProperty("entries")
    List<BoxFileMiniMixin> entries;
    @JsonProperty("order")
    List<BoxOrderMixin> order;
}