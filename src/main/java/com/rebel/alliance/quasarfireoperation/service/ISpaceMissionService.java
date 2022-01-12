package com.rebel.alliance.quasarfireoperation.service;

import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteListTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteTopSecretSplit;
import com.rebel.alliance.quasarfireoperation.entity.controller.ResponseShipTopSecret;

public interface ISpaceMissionService {
	
	public ResponseShipTopSecret getInformationShip(RequestSatelliteListTopSecret satelliteList);
	
	public ResponseShipTopSecret getInformationShip();
	
	public void saveInformationSatellite(RequestSatelliteTopSecretSplit satellite, String satelliteName);
}
