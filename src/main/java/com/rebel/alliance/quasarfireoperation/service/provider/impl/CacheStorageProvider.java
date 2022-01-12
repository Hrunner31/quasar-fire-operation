package com.rebel.alliance.quasarfireoperation.service.provider.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;
import com.rebel.alliance.quasarfireoperation.service.provider.IStorageProvider;

@Service
public class CacheStorageProvider implements IStorageProvider {

	private CacheManager cacheManager;

	public CacheStorageProvider(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public List<Satellite> getSatellites() {
        List<Satellite> satellites = new ArrayList<Satellite>();
		this.cacheManager.getCacheNames().forEach(name ->{ 
			Satellite satellite = this.cacheManager.getCache(name).get(name, Satellite.class);
			satellites.add(satellite) ;
		});
		return satellites;

	}

	@Override
	public void saveSatellite(Satellite satellite) {
		this.cacheManager.getCache(satellite.getName()).put(satellite.getName(), satellite);
	}

}
