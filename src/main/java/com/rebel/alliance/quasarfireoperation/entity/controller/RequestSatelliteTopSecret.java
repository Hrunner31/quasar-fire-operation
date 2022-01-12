package com.rebel.alliance.quasarfireoperation.entity.controller;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSatelliteTopSecret implements Serializable {
	
	private static final long serialVersionUID = -2220761955073912710L;

	private float distance;
	
	private String name;
	
	private String[] message;
}
