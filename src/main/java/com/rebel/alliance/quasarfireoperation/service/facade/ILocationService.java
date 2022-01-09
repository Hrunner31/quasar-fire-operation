package com.rebel.alliance.quasarfireoperation.service.facade;

import com.rebel.alliance.quasarfireoperation.entity.Position;

public interface ILocationService {
	public Position getLocation(float... distances);
}
