package com.rebel.alliance.quasarfireoperation.entity.service;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Satellite extends ArtificialSatellite implements Serializable{
	
	private static final long serialVersionUID = -3314439474620760476L;

	public Satellite(Position position) {
		super(position);
	}
	
	@Id
	private String name;
	private float distance;
	private String[] message;
}
