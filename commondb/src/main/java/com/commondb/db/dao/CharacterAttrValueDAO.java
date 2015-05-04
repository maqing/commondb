package com.commondb.db.dao;

import com.commondb.db.bo.CharacterAttrValue;
import com.commondb.db.bo.CharacterAttrValueCriteria;
import java.util.List;

public abstract interface CharacterAttrValueDAO
{
  public abstract int countByExample(CharacterAttrValueCriteria paramCharacterAttrValueCriteria);
  
  public abstract int deleteByExample(CharacterAttrValueCriteria paramCharacterAttrValueCriteria);
  
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract void insert(CharacterAttrValue paramCharacterAttrValue);
  
  public abstract void insertSelective(CharacterAttrValue paramCharacterAttrValue);
  
  public abstract List selectByExample(CharacterAttrValueCriteria paramCharacterAttrValueCriteria);
  
  public abstract CharacterAttrValue selectByPrimaryKey(Integer paramInteger);
  
  public abstract int updateByExampleSelective(CharacterAttrValue paramCharacterAttrValue, CharacterAttrValueCriteria paramCharacterAttrValueCriteria);
  
  public abstract int updateByExample(CharacterAttrValue paramCharacterAttrValue, CharacterAttrValueCriteria paramCharacterAttrValueCriteria);
  
  public abstract int updateByPrimaryKeySelective(CharacterAttrValue paramCharacterAttrValue);
  
  public abstract int updateByPrimaryKey(CharacterAttrValue paramCharacterAttrValue);
}
