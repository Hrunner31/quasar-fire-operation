package com.rebel.alliance.quasarfireoperation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.entity.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.SatelliteList;
import com.rebel.alliance.quasarfireoperation.entity.Ship;
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
	public Ship getInformationShip(SatelliteList satelliteList) {
		Ship response = this.secretSpaceMissionService.getShip(satelliteList);
		return response;
	}

	@Override
	public Ship getInformationShip() {
		Ship response = this.secretSpaceMissionService.getShip(null);
		return response;
	}

	@Override
	public void saveInformationSatellite(Satellite satellite, String satelliteName) {
		// TODO Auto-generated method stub
		
	}
}
