package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.env.Environment;

import com.rebel.alliance.quasarfireoperation.entity.service.Position;
import com.rebel.alliance.quasarfireoperation.service.facade.ILocationService;
import com.rebel.alliance.quasarfireoperation.utilities.Utility;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LocationServiceTest {

	@Mock
	private Environment environment;

	@Mock
	private Utility utility;

	public static final String SATELLITE = "{\"satellites\":[{\"name\":\"Kenobi\",\"distance\":null,\"position\":{\"x\":-500,\"y\":-200},\"message\":null},{\"name\":\"Skywalker\",\"distance\":null,\"position\":{\"x\":100,\"y\":-100},\"message\":null},{\"name\":\"Sato\",\"distance\":null,\"position\":{\"x\":500,\"y\":100},\"message\":null}]}";

	@Test
	public void givenPositionValid_whenDistanceListSameThatPositions_thenReturnPositionShip() {
		Position positionExpected = new Position(-58.31524858127357, -69.55141718433059);
		float d1 = (float) 100.0;
		float d2 = (float) 142.7;
		float d3 = (float) 115.5;
		float[] distanceArray = { d1, d2, d3 };
		double[] distanceArrayDouble = { d1, d2, d3 };
		ILocationService locationService = new LocationService(environment, utility);
		when(environment.getProperty("satellites.json")).thenReturn(SATELLITE);
		when(utility.floatToDoubleArray(distanceArray)).thenReturn(distanceArrayDouble);
		Position positionConsulted = locationService.getLocation(d1, d2, d3);
		Assertions.assertEquals(positionConsulted, positionExpected);
	}

	@Test
	public void givenPositionInvalid_whenDistanceListGreaterThanNumberSatellites_thenReturnBadRequest() {
		float d1 = (float) 100.0;
		float d2 = (float) 142.7;
		float d3 = (float) 115.5;
		float d4 = (float) 120.5;
		float[] distanceArray = { d1, d2, d3, d4 };
		double[] distanceArrayDouble = { d1, d2, d3, d4 };
		ILocationService locationService = new LocationService(environment, utility);
		when(environment.getProperty("satellites.json")).thenReturn(SATELLITE);
		when(utility.floatToDoubleArray(distanceArray)).thenReturn(distanceArrayDouble);
		try {
			locationService.getLocation(d1, d2, d3, d4);
		} catch (Exception e) {
			Assertions.assertEquals(
					"Bad Request Location(400) El número de posiciones es diferente al número de distancias",
					e.getMessage());
		}
	}
	
	@Test
	public void givenPositionInvalid_whenDistanceListLessThanNumberSatellites_thenReturnBadRequest() {
		float d1 = (float) 100.0;
		float d2 = (float) 142.7;
		float[] distanceArray = { d1, d2 };
		double[] distanceArrayDouble = { d1, d2 };
		ILocationService locationService = new LocationService(environment, utility);
		when(environment.getProperty("satellites.json")).thenReturn(SATELLITE);
		when(utility.floatToDoubleArray(distanceArray)).thenReturn(distanceArrayDouble);
		try {
			locationService.getLocation(d1, d2);
		} catch (Exception e) {
			Assertions.assertEquals(
					"Bad Request Location(400) El número de posiciones es diferente al número de distancias",
					e.getMessage());
		}
	}
}
