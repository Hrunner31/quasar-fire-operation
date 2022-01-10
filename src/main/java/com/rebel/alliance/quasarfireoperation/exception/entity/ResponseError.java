package com.rebel.alliance.quasarfireoperation.exception.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError implements Serializable {
	
	private static final long serialVersionUID = 1223825736108478285L;
	
	private int code;
	
	private String message;
}
