package com.rebel.alliance.quasarfireoperation.exception;

public class FieldInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2772501927223715298L;

	private static final String DESCRIPTION = "Field Invalid(400)";

	public FieldInvalidException(String message) {
		super(DESCRIPTION + " " + message);
	}
}
