package com.rebel.alliance.quasarfireoperation.service.facade;

import com.rebel.alliance.quasarfireoperation.entity.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.SatelliteList;
import com.rebel.alliance.quasarfireoperation.entity.Ship;

public interface ISecretSpaceMissionService {
	
	public Ship getShip(SatelliteList satelliteList);
	
	public void saveSatellite(Satellite satellite);

}
