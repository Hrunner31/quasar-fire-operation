package com.rebel.alliance.quasarfireoperation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebel.alliance.quasarfireoperation.entity.Satellite;
import com.rebel.alliance.quasarfireoperation.entity.SatelliteList;
import com.rebel.alliance.quasarfireoperation.entity.Ship;
import com.rebel.alliance.quasarfireoperation.service.impl.SpaceMissionServiceImpl;

@RestController
@RequestMapping(path = "${api.version}")
public class SpaceMissionController {

	private SpaceMissionServiceImpl spaceMissionService;

	public SpaceMissionController(SpaceMissionServiceImpl spaceMissionService) {
		this.spaceMissionService = spaceMissionService;
	}

	@PostMapping("/topsecret/")
	public ResponseEntity<Ship> topSecret(@RequestBody SatelliteList satelliteList) {
		Ship response = this.spaceMissionService.getInformationShip(satelliteList);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/topsecret_split/{satellite}")
	public void topSecretSplit(
			@RequestBody Satellite satellite,
			@PathVariable(value = "satellite") String satelliteName) {
		this.spaceMissionService.saveInformationSatellite(satellite, satelliteName);
	}

	@GetMapping("/topsecret/")
	public ResponseEntity<Ship> topSecretSplit() {
		Ship response = this.spaceMissionService.getInformationShip();
		return ResponseEntity.ok(response);
	}

}
