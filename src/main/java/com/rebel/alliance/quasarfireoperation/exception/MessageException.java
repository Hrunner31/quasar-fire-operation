package com.rebel.alliance.quasarfireoperation.exception;

public class MessageException extends RuntimeException {
	
	private static final long serialVersionUID = 3047681577478313798L;
	
	private static final String DESCRIPTION = "Bad Request Message(400)";
	
	public MessageException(String message) {
		super(DESCRIPTION + " " + message);
	}
}
