package com.rebel.alliance.quasarfireoperation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rebel.alliance.quasarfireoperation.entity.service.Satellite;

@Repository
public interface SatelliteRepository extends CrudRepository<Satellite, String> {

}
