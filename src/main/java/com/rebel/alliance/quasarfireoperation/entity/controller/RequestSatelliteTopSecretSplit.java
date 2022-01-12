package com.rebel.alliance.quasarfireoperation.entity.controller;

import java.beans.Transient;
import java.io.Serializable;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.exception.FieldInvalidException;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;

import lombok.Data;

@Data
public class RequestSatelliteTopSecretSplit implements Serializable {

	private static final long serialVersionUID = -7056522008200399639L;

	private float distance;
	
	private String[] message;
	
	@Transient
	public Satellite convertToEntityService(String satelliteName) {
		validateFields();
		Satellite satellite = new Satellite();
		satellite.setDistance(distance);
		satellite.setMessage(message);
		satellite.setName(satelliteName);
		return satellite;
	}
	
	@Transient
	private void validateFields() {
		if (message == null) {
			throw new FieldInvalidException(Constant.FIELD_INVALID);
		}
	}
}
