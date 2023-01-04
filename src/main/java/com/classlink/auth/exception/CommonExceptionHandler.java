package com.classlink.auth.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.classlink.auth.domain.result.ResponseResult;

@RestController
@ControllerAdvice
public class CommonExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	@ExceptionHandler(value = CustomException.class)
	public ResponseResult<?> customException(CustomException ex) {
		if(ex.getStatusCode() == 400){
			return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), ex.getErrorCode(), ex.getErrorMessage());
		}else {
			return new ResponseResult<>(HttpStatus.OK.value(), ex.getErrorCode(), ex.getErrorMessage());
		}
	}

	/*
	 * @ExceptionHandler(value = SQLException.class) public ResponseResult<?>
	 * sqlException(SQLException ex) {
	 * 
	 * return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
	 * HttpStatus.INTERNAL_SERVER_ERROR.toString(),
	 * ErrorCode.CODE_07.getErrorCode(), ex.getMessage()); }
	 */
}
