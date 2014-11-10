package org.springframework.social.box.domain.json;

import java.util.List;

import org.springframework.social.box.domain.internal.json.BoxFileMiniMixin;
import org.springframework.social.box.domain.internal.json.BoxOrderMixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxFolderItemsMixin {
    @JsonProperty("total_count")
    private Integer totalCount;
    @JsonProperty("offset")
	private Integer offset;
    @JsonProperty("limit")
	private Integer limit;
    @JsonProperty("entries")
	private List<BoxFileMiniMixin> entries;
    @JsonProperty("order")
	private List<BoxOrderMixin> order;
}