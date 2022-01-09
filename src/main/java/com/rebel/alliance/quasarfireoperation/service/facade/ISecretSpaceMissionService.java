package com.rebel.alliance.quasarfireoperation.service.facade;

import com.rebel.alliance.quasarfireoperation.entity.ArtificialSatellite;
import com.rebel.alliance.quasarfireoperation.entity.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.SatelliteList;

public interface ISecretSpaceMissionService {
	
	public ArtificialSatellite getShip(SatelliteList satelliteList);
	
	public void saveSatellite(Satellite satellite);

}
