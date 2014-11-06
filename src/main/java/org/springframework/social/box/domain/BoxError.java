package org.springframework.social.box.domain;

import org.springframework.social.box.domain.enums.BoxItemType;
import org.springframework.social.box.domain.internal.BoxObject;

public class BoxError extends BoxObject {
	private BoxItemType type;
	private Integer status;
	private String code;
	private String helpUrl;
	private String message;
	private String requestId;

	public BoxItemType getType() {
		return type;
	}
	public Integer getStatus() {
		return status;
	}
	public String getCode() {
		return code;
	}
	public String getHelpUrl() {
		return helpUrl;
	}
	public String getMessage() {
		return message;
	}
	public String getRequestId() {
		return requestId;
	}
}