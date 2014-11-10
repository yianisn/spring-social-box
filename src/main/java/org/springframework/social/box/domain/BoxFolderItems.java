package org.springframework.social.box.domain;

import java.util.List;

import org.springframework.social.box.domain.internal.BoxObject;
import org.springframework.social.box.domain.internal.json.BoxFileMiniMixin;
import org.springframework.social.box.domain.internal.json.BoxOrderMixin;

public class BoxFolderItems extends BoxObject {
	private Integer totalCount;
	private Integer offset;
	private Integer limit;
	private List<BoxFileMiniMixin> entries;
	private List<BoxOrderMixin> order;

	public Integer getTotalCount() {
		return totalCount;
	}
	public Integer getOffset() {
		return offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public List<BoxFileMiniMixin> getEntries() {
		return entries;
	}
	public List<BoxOrderMixin> getOrder() {
		return order;
	}
}