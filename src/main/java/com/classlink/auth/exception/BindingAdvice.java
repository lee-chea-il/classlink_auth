package com.classlink.auth.exception;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.classlink.auth.domain.result.ResponseResult;

@Component
@Aspect
public class BindingAdvice {
	// @After
	// @Beforef
	@Around("execution(* com.classlink.web.api.controller..*Controller.*(..))")
	public Object vaildCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;

				if (bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();

					for (FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					return new ResponseResult(HttpStatus.OK.value(), errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
