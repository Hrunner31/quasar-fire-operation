package com.rebel.alliance.quasarfireoperation.entity.service;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SatellitePosition implements Serializable {
	
	private static final long serialVersionUID = -513902409801876693L;
	private List<Satellite> satellites;
}
