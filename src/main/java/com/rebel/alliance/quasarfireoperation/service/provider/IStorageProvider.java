package com.rebel.alliance.quasarfireoperation.service.provider;

import java.util.List;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;

public interface IStorageProvider {

	List<Satellite> getSatellites();

	void saveSatellite(Satellite satellite);

}
