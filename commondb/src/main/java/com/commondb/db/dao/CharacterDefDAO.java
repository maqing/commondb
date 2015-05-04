package com.commondb.db.dao;

import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.CharacterDefCriteria;
import java.util.List;

public abstract interface CharacterDefDAO
{
  public abstract int countByExample(CharacterDefCriteria paramCharacterDefCriteria);
  
  public abstract int deleteByExample(CharacterDefCriteria paramCharacterDefCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(CharacterDef paramCharacterDef);
  
  public abstract void insertSelective(CharacterDef paramCharacterDef);
  
  public abstract List selectByExample(CharacterDefCriteria paramCharacterDefCriteria);
  
  public abstract CharacterDef selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(CharacterDef paramCharacterDef, CharacterDefCriteria paramCharacterDefCriteria);
  
  public abstract int updateByExample(CharacterDef paramCharacterDef, CharacterDefCriteria paramCharacterDefCriteria);
  
  public abstract int updateByPrimaryKeySelective(CharacterDef paramCharacterDef);
  
  public abstract int updateByPrimaryKey(CharacterDef paramCharacterDef);
  
  public abstract int updateShare(CharacterDef paramCharacterDef);
  
  public abstract int updateEnable(CharacterDef paramCharacterDef);
  
  public abstract int updateCheckMultiple(CharacterDef paramCharacterDef);
  
  public abstract List getByMetaId(Integer paramInteger);
}
