package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.rebel.alliance.quasarfireoperation.entity.service.Position;
import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.service.SatellitePosition;
import com.rebel.alliance.quasarfireoperation.exception.LocationException;
import com.rebel.alliance.quasarfireoperation.service.facade.ILocationService;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;
import com.rebel.alliance.quasarfireoperation.utilities.Utility;

/**
 * La clase LocationService es la encargada de validar que las distancias y las posiciones de los satelites sean
 * iguales, tambien se encarga de realizar el calculo de las coordenadas de la posición de la nave rebelde
 * @author H. Leonardo A. Corredor
 *
 */
@Service
public class LocationService implements ILocationService {

	private Environment environment;
	
	private Utility utility;

	public LocationService(Environment environment, Utility utility) {
		this.environment = environment;
		this.utility = utility;
	}

	/** El método getLocation es el encargado de orquestar la logica para el calculo de las coordenadas 
	 * de la posición de la nave rebelde
	 * @param distances: Lista de la distancia de cada uno de los satelites
	 * @return Position: Coordenadas del eje X y Y de la nave rebelde
	 */
	@Override
	public Position getLocation(float... distances) {
		double[] position = { 0, 0 };
		double[][] positions = getPositions();
		if(positions.length != distances.length) {
		  throw new LocationException(Constant.SATELLITE_POSITION_DISTANCE_ERROR);
		}
		double[] doubleDistances = this.utility.floatToDoubleArray(distances);
		TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, doubleDistances);
		NonLinearLeastSquaresSolver positionSolve = new NonLinearLeastSquaresSolver(trilaterationFunction,
				new LevenbergMarquardtOptimizer());
		position = positionSolve.solve().getPoint().toArray();
		return new Position(position[0], position[1]);
	}

	/**
	 * El método getPositions está encargado de orquestar la validación de que haya algún satelite almacenado en
	 * en la aplicación y de hacer el llamado para la construcción de la matriz de posiciones
	 * @return double[][]: Matriz de posiciones
	 */
	private double[][] getPositions() {
		String satellitesJson = environment.getProperty("satellites.json");
		SatellitePosition satellitePosition = new Gson().fromJson(satellitesJson, SatellitePosition.class);
		double[][] positionMatrix = null;
		if (satellitePosition == null) {
			throw new LocationException(Constant.SATELLITE_POSITION_ERROR);
		} else {
			Collections.sort(satellitePosition.getSatellites(), (x, y) -> x.getName().compareToIgnoreCase(y.getName()));
			List<Satellite> satelliteList = satellitePosition.getSatellites();
			if (satelliteList != null) {
				int satelliteNumber = satelliteList.size();
				if (satelliteNumber < Constant.SATELLITE_NUMBERS) {
					throw new LocationException(Constant.SATELLITE_MIN_POSITION_ERROR);
				}
				positionMatrix = setPositionsMatrix(satelliteNumber, Constant.POSITION_COLUMN, positionMatrix, satelliteList);
			} else {
				throw new LocationException(Constant.SATELLITE_POSITION_ERROR);
			}
		}
		return positionMatrix;
	}

	/**
	 * El método setPositionsMatrix se encarga de construir la matriz de acuerdo a las posiciones de los satelites
	 * @param rowSize: Número de posiciones que va a tener las filas de la matriz
	 * @param columnSize: Número de posiciones que va a tener las filas de la matriz
	 * @param positionMatrix: Matriz de posiciones en X y Y
	 * @param satelliteList: Lista de satelites almacenados en la aplicación
	 * @return double[][]: Matriz de posiciones
	 */
	private double[][] setPositionsMatrix(int rowSize, int columnSize, double[][] positionMatrix,
			List<Satellite> satelliteList) {
		positionMatrix = new double[rowSize][Constant.POSITION_COLUMN];
		for (int i = 0; i < rowSize; i++) {
			int j = 0;
			positionMatrix[i][j] = satelliteList.get(i).getPosition().getX();
			positionMatrix[i][j + 1] = satelliteList.get(i).getPosition().getY();
		}
		return positionMatrix;
	}

}
