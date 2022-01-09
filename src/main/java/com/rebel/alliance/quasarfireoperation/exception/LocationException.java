package com.rebel.alliance.quasarfireoperation.exception;

public class LocationException extends RuntimeException {
	
	private static final long serialVersionUID = 2496483400676335925L;
	
	private static final String DESCRIPTION = "Bad Request Location(400)";
	
	public LocationException(String message) {
		super(DESCRIPTION + " " + message);
	}
}