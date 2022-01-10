package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.rebel.alliance.quasarfireoperation.entity.ArtificialSatellite;
import com.rebel.alliance.quasarfireoperation.entity.Position;
import com.rebel.alliance.quasarfireoperation.entity.SatellitePosition;
import com.rebel.alliance.quasarfireoperation.exception.LocationException;
import com.rebel.alliance.quasarfireoperation.service.facade.ILocationService;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;
import com.rebel.alliance.quasarfireoperation.utilities.Utility;

@Service
public class LocationService implements ILocationService {

	private Environment environment;
	private Utility utility;

	@Autowired
	public LocationService(Environment environment, Utility utility) {
		this.environment = environment;
		this.utility = utility;
	}

	@Override
	public Position getLocation(float... distances) {
		double[] position = { 0, 0 };
		double[][] positions = getPositions();
		double[] doubleDistances = this.utility.floatToDoubleArray(distances);
		TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, doubleDistances);
		NonLinearLeastSquaresSolver positionSolve = new NonLinearLeastSquaresSolver(trilaterationFunction,
				new LevenbergMarquardtOptimizer());
		position = positionSolve.solve().getPoint().toArray();
		return new Position(position[0], position[1]);
	}

	private double[][] getPositions() {
		String satellitesJson = environment.getProperty("satellites.json");
		SatellitePosition satellitePosition = new Gson().fromJson(satellitesJson, SatellitePosition.class);
		double[][] positionMatrix = null;
		if (satellitePosition == null) {
			throw new LocationException(Constant.SATELLITE_POSITION_ERROR);
		} else {
			List<ArtificialSatellite> satelliteList = satellitePosition.getPositions();
			if (satelliteList != null) {
				int satelliteNumber = satelliteList.size();
				if (satelliteNumber < 3) {
					throw new LocationException(Constant.SATELLITE_MIN_POSITION_ERROR);
				}
				positionMatrix = setPositionsMatrix(satelliteNumber, Constant.POSITION_COLUMN, positionMatrix, satelliteList);
			} else {
				throw new LocationException(Constant.SATELLITE_POSITION_ERROR);
			}
		}
		return positionMatrix;
	}

	private double[][] setPositionsMatrix(int rowSize, int columnSize, double[][] positionMatrix,
			List<ArtificialSatellite> satelliteList) {
		positionMatrix = new double[rowSize][Constant.POSITION_COLUMN];
		for (int i = 0; i < rowSize; i++) {
			int j = 0;
			positionMatrix[i][j] = satelliteList.get(i).getPosition().getX();
			positionMatrix[i][j + 1] = satelliteList.get(i).getPosition().getY();
		}
		return positionMatrix;
	}

}
