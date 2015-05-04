package com.commondb.db.dao;

import com.commondb.db.bo.CharacterAttrDef;
import com.commondb.db.bo.CharacterAttrDefCriteria;
import java.util.List;

public abstract interface CharacterAttrDefDAO
{
  public abstract int countByExample(CharacterAttrDefCriteria paramCharacterAttrDefCriteria);
  
  public abstract int deleteByExample(CharacterAttrDefCriteria paramCharacterAttrDefCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract Integer insert(CharacterAttrDef paramCharacterAttrDef);
  
  public abstract void insertSelective(CharacterAttrDef paramCharacterAttrDef);
  
  public abstract List selectByExample(CharacterAttrDefCriteria paramCharacterAttrDefCriteria);
  
  public abstract CharacterAttrDef selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(CharacterAttrDef paramCharacterAttrDef, CharacterAttrDefCriteria paramCharacterAttrDefCriteria);
  
  public abstract int updateByExample(CharacterAttrDef paramCharacterAttrDef, CharacterAttrDefCriteria paramCharacterAttrDefCriteria);
  
  public abstract int updateByPrimaryKeySelective(CharacterAttrDef paramCharacterAttrDef);
  
  public abstract int updateByPrimaryKey(CharacterAttrDef paramCharacterAttrDef);
}
