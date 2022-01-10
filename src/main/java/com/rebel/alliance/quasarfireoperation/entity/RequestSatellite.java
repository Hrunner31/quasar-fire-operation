package com.rebel.alliance.quasarfireoperation.entity;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSatellite {
	
	@NotBlank(message = "Distancia del satelite es obligatorio")
	private float distance;
	
	@NotBlank(message = "Nombre del satelite es obligatorio")
	private String name;
	
	@NotBlank(message = "Mensaje del satelite es obligatorio")
	private String[] message;
}
