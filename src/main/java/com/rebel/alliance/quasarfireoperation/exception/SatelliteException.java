package com.rebel.alliance.quasarfireoperation.exception;

public class SatelliteException extends RuntimeException {
	
	private static final long serialVersionUID = -6504183924213802299L;
	
	private static final String DESCRIPTION = "Bad Request Location(400)";
	
	public SatelliteException(String message) {
		super(DESCRIPTION + " " + message);
	}
}