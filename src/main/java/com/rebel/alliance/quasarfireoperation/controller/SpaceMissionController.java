package com.rebel.alliance.quasarfireoperation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteListTopSecret;
import com.rebel.alliance.quasarfireoperation.entity.controller.RequestSatelliteTopSecretSplit;
import com.rebel.alliance.quasarfireoperation.entity.controller.ResponseShipTopSecret;
import com.rebel.alliance.quasarfireoperation.service.ISpaceMissionService;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;

@RestController
@RequestMapping(path = "${api.version}")
public class SpaceMissionController {

	private ISpaceMissionService spaceMissionService;

	public SpaceMissionController(ISpaceMissionService spaceMissionService) {
		this.spaceMissionService = spaceMissionService;
	}

	@PostMapping("/topsecret/")
	public ResponseEntity<ResponseShipTopSecret> topSecret(@RequestBody RequestSatelliteListTopSecret satelliteList) {
		ResponseShipTopSecret response = this.spaceMissionService.getInformationShip(satelliteList);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/topsecret_split/{satellite}")
	public ResponseEntity<String> topSecretSplit(
			@RequestBody RequestSatelliteTopSecretSplit satellite,
			@PathVariable(value = "satellite") String satelliteName) {
		this.spaceMissionService.saveInformationSatellite(satellite, satelliteName);
		return ResponseEntity.status(HttpStatus.OK).body(Constant.RESPONSE_MESSAGE);
	}

	@GetMapping("/topsecret_split/")
	public ResponseEntity<ResponseShipTopSecret> topSecretSplit() {
		ResponseShipTopSecret response = this.spaceMissionService.getInformationShip();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
