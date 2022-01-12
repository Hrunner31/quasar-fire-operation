package com.rebel.alliance.quasarfireoperation.entity.controller;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.service.SatelliteList;
import com.rebel.alliance.quasarfireoperation.exception.FieldInvalidException;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;

import lombok.Data;

@Data
public class RequestSatelliteListTopSecret implements Serializable {

	private static final long serialVersionUID = -8418018453242351186L;

	private List<RequestSatelliteTopSecret> satellites;

	@Transient
	public SatelliteList convertToEntityService() {

		SatelliteList returnList = new SatelliteList();
		List<Satellite> satelliteList = new ArrayList<Satellite>();
		validateFields();
		satellites.forEach(s -> {
			Satellite satellite = new Satellite();
			satellite.setName(s.getName());
			satellite.setDistance(s.getDistance());
			satellite.setMessage(s.getMessage());
			satelliteList.add(satellite);
		});

		returnList.setSatellites(satelliteList);
		return returnList;
	}

	@Transient
	private void validateFields() {
		if (satellites == null) {
			throw new FieldInvalidException(Constant.FIELD_INVALID);
		}
		satellites.stream().forEach(s -> {
			boolean invalid = s.getName() == null || s.getName().isEmpty() || s.getMessage() == null;
			if (invalid)
				throw new FieldInvalidException(Constant.FIELD_INVALID);
		});
	}

}
