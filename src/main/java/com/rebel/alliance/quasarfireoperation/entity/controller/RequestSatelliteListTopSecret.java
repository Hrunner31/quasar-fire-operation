package com.rebel.alliance.quasarfireoperation.entity.controller;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.service.SatelliteList;

import lombok.Data;

@Data
public class RequestSatelliteListTopSecret {

	private List<RequestSatelliteTopSecret> satellites;
	
	@Transient
	public SatelliteList convertToEntityService() {
		SatelliteList returnList = new SatelliteList();
		List<Satellite> satelliteList = new ArrayList<Satellite>();
		satellites.forEach(s -> {
			Satellite satellite = new Satellite();
			satellite.setName(s.getName());
			satellite.setDistance(s.getDistance());
			satellite.setMessage(s.getMessage());
			satelliteList.add(satellite);
		});
		returnList.setSatellites(satelliteList);
		return returnList;
	}
}
