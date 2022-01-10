package com.rebel.alliance.quasarfireoperation.entity;

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
}
