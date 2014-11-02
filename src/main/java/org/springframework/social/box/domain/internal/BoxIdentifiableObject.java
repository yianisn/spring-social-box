package org.springframework.social.box.domain.internal;

import org.springframework.social.box.domain.enums.BoxItemType;

public abstract class BoxIdentifiableObject extends BoxObject {
	private String id;
	private BoxItemType type;

	public String getId() {
		return id;
	}

	public BoxItemType getType() {
		return type;
	}

}
