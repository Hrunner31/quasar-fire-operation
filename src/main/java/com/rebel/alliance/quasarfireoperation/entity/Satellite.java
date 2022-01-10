package com.rebel.alliance.quasarfireoperation.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Satellite extends ArtificialSatellite{
	
	public Satellite(Position position) {
		super(position);
	}

	private float distance;
	
	private String name;
	
	private String[] message;
}
