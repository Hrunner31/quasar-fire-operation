package com.rebel.alliance.quasarfireoperation.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteListTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteTopSecretSplit;
import com.rebel.alliance.quasarfireoperation.entity.controller.ResponseShipTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.service.Ship;
import com.rebel.alliance.quasarfireoperation.service.impl.SpaceMissionServiceImpl;

@RestController
@Validated
@RequestMapping(path = "${api.version}")
public class SpaceMissionController {

	private SpaceMissionServiceImpl spaceMissionService;

	@Autowired
	public SpaceMissionController(SpaceMissionServiceImpl spaceMissionService) {
		this.spaceMissionService = spaceMissionService;
	}

	@PostMapping("/topsecret/")
	public ResponseEntity<ResponseShipTopSecret> topSecret(@Valid @RequestBody RequestSatelliteListTopSecret satelliteList) {
		ResponseShipTopSecret response = this.spaceMissionService.getInformationShip(satelliteList);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/topsecret_split/{satellite}")
	public ResponseEntity<?> topSecretSplit(
			@Valid @RequestBody RequestSatelliteTopSecretSplit satellite,
			@PathVariable(value = "satellite") @NotBlank(message = "Nombre del satelite es obligatorio") String satelliteName) {
		this.spaceMissionService.saveInformationSatellite(satellite, satelliteName);
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
	}

	@GetMapping("/topsecret/")
	public ResponseEntity<Ship> topSecretSplit() {
		Ship response = this.spaceMissionService.getInformationShip();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
