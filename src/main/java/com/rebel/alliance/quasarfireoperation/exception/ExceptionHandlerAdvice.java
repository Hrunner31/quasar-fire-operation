package com.rebel.alliance.quasarfireoperation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rebel.alliance.quasarfireoperation.exception.entity.ResponseError;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;
import com.rebel.alliance.quasarfireoperation.utilities.Utility;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	Utility utilities;
	
	@Autowired
	public ExceptionHandlerAdvice(Utility utilities) {
		this.utilities = utilities;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseError handleUnexpectedError(Exception exception) {
		logger.error(Constant.UNEXPECTED_ERROR, this.utilities.getStackError(exception));
		String message = exception.getMessage() != null ? exception.getMessage() : Constant.UNEXPECTED_ERROR;
		return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}
	
	@ExceptionHandler({
		LocationException.class, 
		MessageException.class,
		SatelliteException.class
	})
	@ResponseBody
	public ResponseError handleBusinessError(Exception exception) {
		logger.error(Constant.INDETERMINATE_ERROR, this.utilities.getStackError(exception));
		String message = exception.getMessage() != null ? exception.getMessage() : Constant.INDETERMINATE_ERROR;
		return new ResponseError(HttpStatus.BAD_REQUEST.value(), message);
	}
	
	@ExceptionHandler({
		HttpRequestMethodNotSupportedException.class, 
		MethodArgumentNotValidException.class, 
		MissingServletRequestParameterException.class,
		MethodArgumentTypeMismatchException.class,
		HttpMessageNotReadableException.class
	})
	@ResponseBody
	public ResponseError handleBadRequest(Exception exception) {
		logger.error(Constant.INVALID_REQUEST, this.utilities.getStackError(exception));
		String message = exception.getMessage() != null ? exception.getMessage() : Constant.INVALID_REQUEST;
		return new ResponseError(HttpStatus.BAD_REQUEST.value(), message);
	}
}
