package com.commondb.db.dao;

import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDataCriteria;
import java.util.List;

public abstract interface CharacterDataDAO
{
  public abstract int countByExample(CharacterDataCriteria paramCharacterDataCriteria);
  
  public abstract int deleteByExample(CharacterDataCriteria paramCharacterDataCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(CharacterData paramCharacterData);
  
  public abstract void insertSelective(CharacterData paramCharacterData);
  
  public abstract List selectByExample(CharacterDataCriteria paramCharacterDataCriteria);
  
  public abstract CharacterData selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(CharacterData paramCharacterData, CharacterDataCriteria paramCharacterDataCriteria);
  
  public abstract int updateByExample(CharacterData paramCharacterData, CharacterDataCriteria paramCharacterDataCriteria);
  
  public abstract int updateByPrimaryKeySelective(CharacterData paramCharacterData);
  
  public abstract int updateByPrimaryKey(CharacterData paramCharacterData);
  
  public abstract int updateShare(CharacterData paramCharacterData);
  
  public abstract int updateEnable(CharacterData paramCharacterData);
  
  public abstract List getByEntity(Integer paramInteger, String paramString);
}
