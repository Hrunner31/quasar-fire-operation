package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.entity.service.Position;
import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.service.SatelliteList;
import com.rebel.alliance.quasarfireoperation.entity.service.SatellitePosition;
import com.rebel.alliance.quasarfireoperation.entity.service.Ship;
import com.rebel.alliance.quasarfireoperation.exception.LocationException;
import com.rebel.alliance.quasarfireoperation.exception.MessageException;
import com.rebel.alliance.quasarfireoperation.exception.SatelliteException;
import com.rebel.alliance.quasarfireoperation.service.facade.ISecretSpaceMissionService;
import com.rebel.alliance.quasarfireoperation.service.provider.impl.CacheStorageProvider;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;
import com.rebel.alliance.quasarfireoperation.utilities.Utility;

@Service
public class SecretSpaceMissionService implements ISecretSpaceMissionService {

	LocationService locationService;

	MessageService messageService;

	CacheStorageProvider cacheStorageProvider;

	Utility utilities;

	public SecretSpaceMissionService(LocationService locationService, MessageService messageService,
			CacheStorageProvider cacheStorageProvider, Utility utilities) {
		this.locationService = locationService;
		this.messageService = messageService;
		this.cacheStorageProvider = cacheStorageProvider;
		this.utilities = utilities;
	}

	@Override
	public Ship getShip(SatelliteList satelliteList) {
		Ship ship = null;
		if (satelliteList == null) {
			List<Satellite> satellites = this.cacheStorageProvider.getSatellites();
			SatelliteList satelliteListCache = new SatelliteList();
			satelliteListCache.setSatellites(satellites);
			ship = generateShip(satelliteListCache);
		} else {
			ship = generateShip(satelliteList);
		}
		return ship;
	}

	private Ship generateShip(SatelliteList satelliteList) {
		setSatelliteListFilterBySatelliteName(satelliteList);
		Position position = null;
		String message = "";
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

		return new Ship(position, message);
	}

	private void setSatelliteListFilterBySatelliteName(SatelliteList satelliteList) {
		// Se obtiene la lista de nombres de satelites registrados en la aplicación
		List<String> satelliteNameList = getNamesSatellites();

		// Se filtra la lista enviada por el piloto con respecto a la lista de nombres
		// de satelites registrados en la aplicación
		List<Satellite> satelliteListFilt = satelliteList.getSatellites().stream()
				.filter(satellite -> satelliteNameList.stream().anyMatch(sn -> sn.equals(satellite.getName())))
				.collect(Collectors.toList());

		// Se ordena la lista de satellites obtenida
		Collections.sort(satelliteListFilt, (x, y) -> x.getName().compareToIgnoreCase(y.getName()));
		
		satelliteList.setSatellites(satelliteListFilt);
	}

	private List<String> getNamesSatellites() {
		SatellitePosition satellitePositions = this.utilities.getSatellitesStorageProperties();
		return satellitePositions.getSatellites().stream().map(Satellite::getName).collect(Collectors.toList());
	}

	@Override
	public void saveSatellite(Satellite satellite) {
		this.cacheStorageProvider.saveSatellite(satellite);
	}

}
