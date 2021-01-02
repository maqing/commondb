package com.changan.app.report.service;

import java.util.List;

import com.changan.app.report.model.Cutter;
import com.changan.app.report.model.CutterStat;

public abstract interface CutterStatService
{
  /* 按条件查询刀具的时间指标 */	
  public abstract CutterStat getSingleCutterStat(String lineId, String deviceId, String cutterId,  String timeBegin, String timeEnd);

  /* 查询某类型设备的某型刀具的对比指标 */
  public abstract List<CutterStat> getCutterCompareStat(String lineId, String deviceType, String cutterType, String timeBegin, String timeEnd);

  /* 查询刀具列表 */
  public abstract List<Cutter> getCutterList(String lineId, String deviceType, String deviceId);
}
