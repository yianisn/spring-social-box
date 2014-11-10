package org.springframework.social.box.domain.internal;

import java.util.List;

public class BoxPathCollection extends BoxObject {
	private Integer totalCount;
	private List<BoxFolderMini> entries;

	public Integer getTotalCount() {
		return totalCount;
	}
	public List<BoxFolderMini> getEntries() {
		return entries;
	}
}