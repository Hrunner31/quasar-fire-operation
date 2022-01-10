package com.rebel.alliance.quasarfireoperation.service;

import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteListTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.controller.ResponseShipTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.service.Ship;

public interface ISpaceMissionService {
	
	public ResponseShipTopSecret getInformationShip(RequestSatelliteListTopSecret satelliteList);
	
	public Ship getInformationShip();
	
	public void saveInformationSatellite(Satellite satellite, String satelliteName);
}
