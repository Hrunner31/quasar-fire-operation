package com.rebel.alliance.quasarfireoperation.entity.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SatelliteList {

	private List<Satellite> satellites;

	public float[] getDistances() {
		float[] distances = new float[satellites.size()];
		for (int i = 0; i < satellites.size(); i++) {
			distances[i] = satellites.get(i).getDistance();
		}
		return distances;
	}
	
	public List<String[]> getMessage() {
		List<String[]> messageList = new ArrayList<String[]>();
		satellites.forEach(satellite -> messageList.add(satellite.getMessage()));
		return messageList;
	}
}
