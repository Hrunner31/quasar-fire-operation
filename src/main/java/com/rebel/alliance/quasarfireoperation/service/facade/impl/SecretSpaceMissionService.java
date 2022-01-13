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
import com.rebel.alliance.quasarfireoperation.service.facade.ILocationService;
import com.rebel.alliance.quasarfireoperation.service.facade.IMessageService;
import com.rebel.alliance.quasarfireoperation.service.facade.ISecretSpaceMissionService;
import com.rebel.alliance.quasarfireoperation.service.provider.IStorageProvider;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;
import com.rebel.alliance.quasarfireoperation.utilities.Utility;

/**
 * La clase SecretSpaceMissionService actua como un facade que se encarga de manejar las peticiones del 
 * servicio para calcular las coordenadas de la posición y de construir el mensaje enviado por la nave rebelde
 * @author H. Leonardo A. Corredor
 *
 */
@Service
public class SecretSpaceMissionService implements ISecretSpaceMissionService {

	private ILocationService locationService;

	private IMessageService messageService;

	private IStorageProvider storageProvider;

	private Utility utilities;
	
	public SecretSpaceMissionService(ILocationService locationService, IMessageService messageService,
			IStorageProvider storageProvider, Utility utilities) {
		this.locationService = locationService;
		this.messageService = messageService;
		this.storageProvider = storageProvider;
		this.utilities = utilities;
	}
	
	/**
	 * El método getShip se encarga de la logica para calcular las coordenas y la 
	 * construcción del mensaje enviado por la nave rebelde.
	 * @param satelliteList: Lista de satelites que puede ser null
	 * @return Ship: Posición y mensaje de la nave rebelde
	 */
	@Override
	public Ship getShip(SatelliteList satelliteList) {
		Ship ship = null;
		if (satelliteList == null) { //Si la lista de satelites es nulla, obtiene la lista almacenada parcialmente en la cache para hacer el calculo
			List<Satellite> satellites = this.storageProvider.getSatellites();
			SatelliteList satelliteListCache = new SatelliteList();
			satelliteListCache.setSatellites(satellites);
			ship = generateShip(satelliteListCache);
		} else { // 
			ship = generateShip(satelliteList);
		}
		return ship;
	}

	/**
	 * El método generateShip se encarga de orquestar los llamados a los servicios facade de position y de
	 * message, esto si cumple con las validaciones del número de distancias sea igual al numero de posicione
	 * de los satelites alojados en el archivo de properties.
	 * @param satelliteList: Lista de satelites 
	 * @return Ship: Posición y mensaje de la nave rebelde
	 */
	private Ship generateShip(SatelliteList satelliteList) {
		setSatelliteListFilterBySatelliteName(satelliteList); 
		Position position = null;
		String message = "";
		if (satelliteList.getSatellites() != null) {
			float[] distances = satelliteList.getDistances();
			if (distances.length < Constant.SATELLITE_NUMBERS ) {
				throw new LocationException(Constant.DISTANNCES_ERROR);
			}
			if (satelliteList.getMessage().size() < Constant.SATELLITE_NUMBERS ) {
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

	/**
	 * El metodo setSatelliteListFilterBySatelliteName filtra los satelites enviados con respecto a los satelites
	 * almacenados para no realizar un mal calculo al no tener la información de la posición de dicho satelite
	 * y de no errar a la hora de hacer el calculo para enviar la posición y se encarga de ordenar alfabeticamente
	 * la lista de satelites 
	 * @param satelliteList: Lista de satelites 
	 */
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

	/**
	 * El método getNamesSatellites consulta la lista de satelites almacenados en el archivo properties de la 
	 * aplicación para crear una lista de nombres de los satelites almacenados
	 * @return List<String>: Lista de Strings con los nombres de los satelites almacenados
	 */
	private List<String> getNamesSatellites() {
		SatellitePosition satellitePositions = this.utilities.getSatellitesStorageProperties();
		return satellitePositions.getSatellites().stream().map(Satellite::getName).collect(Collectors.toList());
	}

	/**
	 * El método saveSatellite se encarga de llamar al proveedor de almacenamiento, en este caso
	 * al cacheStorageProvider para almacenar el satellite enviado por la nave rebelde
	 * @param satellite: Tiene la información correspondiente al stelite, nombre, distancia y mensaje
	 */
	@Override
	public void saveSatellite(Satellite satellite) {
		this.storageProvider.saveSatellite(satellite);
	}

}
