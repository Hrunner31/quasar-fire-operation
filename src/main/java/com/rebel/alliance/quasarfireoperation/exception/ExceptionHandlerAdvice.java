package com.rebel.alliance.quasarfireoperation.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rebel.alliance.quasarfireoperation.exception.entity.ResponseError;
import com.rebel.alliance.qusarfireoperation.utilities.Constant;
import com.rebel.alliance.qusarfireoperation.utilities.Utility;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	private static final Logger logger = LogManager.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseError handleUnexpectedError(Exception exception) {
		logger.error(Constant.UNEXPECTED_ERROR, Utility.getStackError(exception));
		String message = exception.getMessage() != null ? exception.getMessage() : Constant.UNEXPECTED_ERROR;
		return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}
	
	@ExceptionHandler({
		LocationException.class, 
		MessageException.class
	})
	public ResponseError handleBusinessError(Exception exception) {
		logger.error(Constant.INDETERMINATE_ERROR, Utility.getStackError(exception));
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
	public ResponseError handleBadRequest(Exception exception) {
		logger.error(Constant.INVALID_REQUEST, Utility.getStackError(exception));
		String message = exception.getMessage() != null ? exception.getMessage() : Constant.INVALID_REQUEST;
		return new ResponseError(HttpStatus.BAD_REQUEST.value(), message);
	}
}
