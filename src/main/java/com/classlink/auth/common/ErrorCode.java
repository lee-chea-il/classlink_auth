package com.classlink.auth.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
	// COMMON
	CODE_01("01", "요청하신 목록이 존재하지 않습니다."),
	CODE_90("90", "토큰이 존재하지 않습니다."),
	CODE_91("91", "토큰이 유효하지 않습니다.");
	private String errorCode;

	private String errorMessage;
	private int errorStatusCode;

	ErrorCode(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	ErrorCode(String errorCode, int statusCode,String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorStatusCode = statusCode;
	}
}
