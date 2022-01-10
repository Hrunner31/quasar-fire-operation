package com.rebel.alliance.quasarfireoperation.entity;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SatelliteList {

	private List<RequestSatellite> satellites;

	@Transient
	public float[] getDistances() {
		float[] distances = new float[satellites.size()];
		for (int i = 0; i < satellites.size(); i++) {
			distances[i] = satellites.get(i).getDistance();
		}
		return distances;
	}
	
	@Transient
	public List<String[]> getMessage() {
		List<String[]> messageList = new ArrayList<String[]>();
		satellites.forEach(satellite -> messageList.add(satellite.getMessage()));
		return messageList;
	}
}
