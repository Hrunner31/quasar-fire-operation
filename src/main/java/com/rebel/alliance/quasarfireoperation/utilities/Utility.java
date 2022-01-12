package com.rebel.alliance.quasarfireoperation.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.rebel.alliance.quasarfireoperation.entity.service.SatellitePosition;

@Component
public class Utility {
	
	private Environment environment;
	
	public Utility(Environment environment) {
		this.environment = environment;
	}
	
	public String getStackError(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return  sw.toString();
	}
	
	public String[][] listToStringArray(List<String[]> list) {
		String[][] array = new String[list.size()][];
		int i = 0;
		for(String[] item: list) {
			array[i++] = item;
		}
		return array;
	}
	
	public double[] floatToDoubleArray(float[] points) {
		double[] pointsDouble = new double[points.length];
		int i= 0;
		for(float point: points) {
			pointsDouble[i++] = point;
		}
		return pointsDouble;
	}
	
	public SatellitePosition getSatellitesStorageProperties() {
		String satellitesJson = environment.getProperty("satellites.json");
		SatellitePosition satellitePosition = new Gson().fromJson(satellitesJson, SatellitePosition.class);
		return satellitePosition;
	}
}
