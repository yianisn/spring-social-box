package org.springframework.social.box.domain.internal;

public abstract class BoxItemObject extends BoxIdentifiableObject {
	private String sequence_id;
	private String etag;
	private String name;

	public String getSequence_id() {
		return sequence_id;
	}
	public String getEtag() {
		return etag;
	}
	public String getName() {
		return name;
	}
}

