package com.commondb.db.dao;

import com.commondb.db.bo.CharacterAttrData;
import com.commondb.db.bo.CharacterAttrDataCriteria;
import java.util.List;

public abstract interface CharacterAttrDataDAO
{
  public abstract int countByExample(CharacterAttrDataCriteria paramCharacterAttrDataCriteria);
  
  public abstract int deleteByExample(CharacterAttrDataCriteria paramCharacterAttrDataCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(CharacterAttrData paramCharacterAttrData);
  
  public abstract void insertSelective(CharacterAttrData paramCharacterAttrData);
  
  public abstract List selectByExample(CharacterAttrDataCriteria paramCharacterAttrDataCriteria);
  
  public abstract CharacterAttrData selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(CharacterAttrData paramCharacterAttrData, CharacterAttrDataCriteria paramCharacterAttrDataCriteria);
  
  public abstract int updateByExample(CharacterAttrData paramCharacterAttrData, CharacterAttrDataCriteria paramCharacterAttrDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(CharacterAttrData paramCharacterAttrData);
  
  public abstract int updateByPrimaryKey(CharacterAttrData paramCharacterAttrData);
}
