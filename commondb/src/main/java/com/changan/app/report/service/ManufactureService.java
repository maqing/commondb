package com.changan.app.report.service;

import java.util.List;

import com.changan.app.report.model.ManufactureInfo;

public abstract interface ManufactureService {
	public abstract List<ManufactureInfo> queryManufactureInfo(String workpieceCode);
	
	public abstract List queryManufactureInfo(String workpieceCode, String timeBegin, String timeEnd, int from, int length);

	public abstract int queryManufactureInfoListSize(String workpieceCode,
			String timeBegin, String timeEnd);

}
