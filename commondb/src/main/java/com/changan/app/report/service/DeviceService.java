package com.changan.app.report.service;

import java.util.List;

import com.changan.app.report.model.Device;

public abstract interface DeviceService {

  public abstract List<Device> getDevice(String lineId);
	
}
