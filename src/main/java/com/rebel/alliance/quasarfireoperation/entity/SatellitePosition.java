package com.rebel.alliance.quasarfireoperation.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SatellitePosition implements Serializable {
	
	private static final long serialVersionUID = -513902409801876693L;
	private List<ArtificialSatellite> positions;
}
