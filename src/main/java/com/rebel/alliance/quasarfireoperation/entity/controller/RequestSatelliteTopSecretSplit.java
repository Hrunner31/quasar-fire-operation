package com.rebel.alliance.quasarfireoperation.entity.controller;

import java.beans.Transient;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestSatelliteTopSecretSplit {

	//@NotNull(message = "Distancia del satelite es obligatorio")
	private float distance;
	
	//@NotBlank(message = "Mensaje del satelite es obligatorio")
	private String[] message;
	
	@Transient
	public Satellite convertToEntityService(String satelliteName) {
		Satellite satellite = new Satellite();
		satellite.setDistance(distance);
		satellite.setMessage(message);
		satellite.setName(satelliteName);
		return satellite;
	}
}
