package com.rebel.alliance.quasarfireoperation.service.facade;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.service.SatelliteList;
import com.rebel.alliance.quasarfireoperation.entity.service.Ship;

public interface ISecretSpaceMissionService {
	
	public Ship getShip(SatelliteList satelliteList);
	
	public void saveSatellite(Satellite satellite);

}
