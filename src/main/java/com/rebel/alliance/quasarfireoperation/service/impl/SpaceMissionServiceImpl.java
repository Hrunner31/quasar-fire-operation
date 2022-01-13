package com.rebel.alliance.quasarfireoperation.service.impl;

import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteListTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteTopSecretSplit;
import com.rebel.alliance.quasarfireoperation.entity.controller.ResponseShipTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.service.Ship;
import com.rebel.alliance.quasarfireoperation.service.ISpaceMissionService;
import com.rebel.alliance.quasarfireoperation.service.facade.ISecretSpaceMissionService;

@Service
public class SpaceMissionServiceImpl implements ISpaceMissionService{
	
	ISecretSpaceMissionService secretSpaceMissionService;
	
	public SpaceMissionServiceImpl(ISecretSpaceMissionService secretSpaceMissionService) {
		this.secretSpaceMissionService = secretSpaceMissionService;
	}

	@Override
	public ResponseShipTopSecret getInformationShip(RequestSatelliteListTopSecret satelliteList) {
		Ship response = this.secretSpaceMissionService.getShip(satelliteList.convertToEntityService());
		return response.convertToEntityController();
	}

	@Override
	public ResponseShipTopSecret getInformationShip() {
		Ship response = this.secretSpaceMissionService.getShip(null);
		return response.convertToEntityController();
	}

	@Override
	public void saveInformationSatellite( RequestSatelliteTopSecretSplit satellite, String satelliteName) {
		this.secretSpaceMissionService.saveSatellite(satellite.convertToEntityService(satelliteName));
	}
}
