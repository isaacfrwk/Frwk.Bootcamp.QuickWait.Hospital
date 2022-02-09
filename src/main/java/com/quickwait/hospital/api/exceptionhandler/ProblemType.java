package com.quickwait.hospital.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_DATA("/invalid-data", "Invalid data"),
	SYSTEM_ERROR("/system-error", "System error"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
	INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Incomprehensible message"),
	RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.title = title;
	}
	
}
