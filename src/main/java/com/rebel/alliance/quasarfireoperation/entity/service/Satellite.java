package com.rebel.alliance.quasarfireoperation.entity.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Satellite extends ArtificialSatellite{
	
	public Satellite(Position position) {
		super(position);
	}

	private float distance;
	
	private String name;
	
	private String[] message;
}
