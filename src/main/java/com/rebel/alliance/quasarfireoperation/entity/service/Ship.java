package com.rebel.alliance.quasarfireoperation.entity.service;

import com.rebel.alliance.quasarfireoperation.entity.controller.ResponseShipTopSecret;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship extends ArtificialSatellite {
	
	private String message;
	
	public Ship(Position position, String message) {
		super(position);
		this.message = message;
	}
	
	public ResponseShipTopSecret convertToEntityController() {
		ResponseShipTopSecret response = new ResponseShipTopSecret();
		response.setMessage(getMessage());
		response.setPosition(getPosition());
		return response;
	}
}
