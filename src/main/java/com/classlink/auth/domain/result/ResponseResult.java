package com.classlink.auth.domain.result;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResponseResult<T> {
	@ApiModelProperty(notes = "http 상태코드", required = true)
	private int statusCode = HttpStatus.OK.value();

	@ApiModelProperty(notes = "응답코드", required = true)
	private String returnCode = "_200";

	@ApiModelProperty(notes = "응답메세지", required = true)
	private String returnMessage;

	@ApiModelProperty(notes = "데이터", required = true)
	private T data;

	public ResponseResult(int statusCode, String returnCode, String returnMessage, T data) {
		super();
		this.statusCode = statusCode;
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
		this.data = data;
	}
	
	public ResponseResult(int statusCode, String returnCode, String returnMessage) {
		super();
		this.statusCode = statusCode;
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}
	
	public ResponseResult(int statusCode, T data) {
		super();
		this.statusCode = statusCode;
		this.data = data;
	}
	
	public ResponseResult(int statusCode) {
		super();
		this.statusCode = statusCode;
	}
	
	public ResponseResult(T data) {
		super();
		this.data = data;
	}
}
