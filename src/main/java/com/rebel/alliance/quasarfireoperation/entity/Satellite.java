package com.rebel.alliance.quasarfireoperation.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Satellite extends ArtificialSatellite{
	private float distance;
	
	private String name;
	
	private String[] message;
}
