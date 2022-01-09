package com.rebel.alliance.quasarfireoperation.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError {
	private int code;
	private String message;
}
