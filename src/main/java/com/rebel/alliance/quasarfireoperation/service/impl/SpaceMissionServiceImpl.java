package com.rebel.alliance.quasarfireoperation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteListTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteTopSecretSplit;
import com.rebel.alliance.quasarfireoperation.entity.controller.ResponseShipTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.service.Ship;
import com.rebel.alliance.quasarfireoperation.service.ISpaceMissionService;
import com.rebel.alliance.quasarfireoperation.service.facade.impl.SecretSpaceMissionService;

@Service
public class SpaceMissionServiceImpl implements ISpaceMissionService{
	
	SecretSpaceMissionService secretSpaceMissionService;
	
	@Autowired
	public SpaceMissionServiceImpl(SecretSpaceMissionService secretSpaceMissionService) {
		this.secretSpaceMissionService = secretSpaceMissionService;
	}

	@Override
	public ResponseShipTopSecret getInformationShip(RequestSatelliteListTopSecret satelliteList) {
		Ship response = this.secretSpaceMissionService.getShip(satelliteList.convertToEntityService());
		return response.convertToEntityController();
	}

	@Override
	public Ship getInformationShip() {
		Ship response = this.secretSpaceMissionService.getShip(null);
		return response;
	}

	@Override
	public void saveInformationSatellite( RequestSatelliteTopSecretSplit satellite, String satelliteName) {
		this.secretSpaceMissionService.saveSatellite(satellite.convertToEntityService(satelliteName));
	}
}
