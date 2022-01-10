package com.rebel.alliance.quasarfireoperation.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Utility {
	
	public String getStackError(Exception e) {
		String response = e.getMessage() + " - ";
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		response += sw.toString();
		return response;
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
	
	
}
