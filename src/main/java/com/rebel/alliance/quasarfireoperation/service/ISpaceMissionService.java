package com.rebel.alliance.quasarfireoperation.service;

import com.rebel.alliance.quasarfireoperation.entity.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.SatelliteList;
import com.rebel.alliance.quasarfireoperation.entity.Ship;

public interface ISpaceMissionService {
	
	public Ship getInformationShip(SatelliteList satelliteList);
	
	public Ship getInformationShip();
	
	public void saveInformationSatellite(Satellite satellite, String satelliteName);
}
