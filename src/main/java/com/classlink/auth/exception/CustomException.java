package com.classlink.auth.exception;



import com.classlink.auth.common.ErrorCode;

import lombok.Data;

public class CustomException extends RuntimeException {

	private ErrorCode error;

	/** 생성자 */
    public CustomException(){
        super();
    }

	/** 생성자 */
    public CustomException(String message){
        super(message);
    }

	/** 열거형 에러 클래스를 인자로 받는 커스텀 생성자 */
    public CustomException(ErrorCode error){
        super(error.getErrorMessage());
        this.error = error;
    }

	/** 에러 클래스에서 에러 메시지 전달 */
	public String getErrorMessage() {
		return this.error.getErrorMessage();
	}

	/** 에러 클래스에서 에러 코드 전달 */
	public String getErrorCode() {
		return this.error.getErrorCode();
	}

	public Integer getStatusCode(){return  this.error.getErrorStatusCode();}

}
