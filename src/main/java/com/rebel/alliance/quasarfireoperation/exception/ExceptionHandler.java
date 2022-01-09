package com.rebel.alliance.quasarfireoperation.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.rebel.alliance.quasarfireoperation.exception.entity.ResponseError;

@ControllerAdvice
public class ExceptionHandler {
	public ResponseError handleUnexpectedError(HttpServletRequest request, Exception exception) {
		ResponseError responseError = new ResponseError();
		return responseError;
	}
	
	public ResponseError handleBusinessError(HttpServletRequest request, Exception exception) {
		ResponseError responseError = new ResponseError();
		return responseError;
	}
	
	public ResponseError handleBadRequest(HttpServletRequest request, Exception exception) {
		ResponseError responseError = new ResponseError();
		return responseError;
	}
}
