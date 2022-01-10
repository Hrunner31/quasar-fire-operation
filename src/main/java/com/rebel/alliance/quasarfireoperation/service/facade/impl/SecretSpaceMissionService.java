package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.entity.service.Position;
import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.service.SatelliteList;
import com.rebel.alliance.quasarfireoperation.entity.service.Ship;
import com.rebel.alliance.quasarfireoperation.exception.LocationException;
import com.rebel.alliance.quasarfireoperation.exception.MessageException;
import com.rebel.alliance.quasarfireoperation.exception.SatelliteException;
import com.rebel.alliance.quasarfireoperation.service.facade.ISecretSpaceMissionService;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;
import com.rebel.alliance.quasarfireoperation.utilities.Utility;

@Service
public class SecretSpaceMissionService implements ISecretSpaceMissionService {

	LocationService locationService;

	MessageService messageService;
	
	Utility utilities;

	@Autowired
	public SecretSpaceMissionService(
			LocationService locationService, 
			MessageService messageService,
			Utility utilities) {
		this.locationService = locationService;
		this.messageService = messageService;
		this.utilities = utilities;
	}

	@Override
	public Ship getShip(SatelliteList satelliteList) {
		Position position = null;
		String message = "";
		if (satelliteList == null) {
			// hacer llamado cache
		} else {
			if (satelliteList.getSatellites() != null) {
				float[] distances = satelliteList.getDistances();
				if (distances.length < 3) {
					throw new LocationException(Constant.DISTANNCES_ERROR);
				}
				if (satelliteList.getMessage().size() < 3) {
					throw new MessageException(Constant.MESSAGE_ERROR);
				}
				// enviar informacion a los servicio de message y location
				position = this.locationService.getLocation(distances);
				String[][] arrayMsg = this.utilities.listToStringArray(satelliteList.getMessage());
				message = this.messageService.getMessage(arrayMsg);
			} else {
				throw new SatelliteException(Constant.SATELLITE_ERROR);
			}
		}
		return new Ship(position, message);
	}

	@Override
	public void saveSatellite(Satellite satellite) {
		// TODO Auto-generated method stub

	}

}
