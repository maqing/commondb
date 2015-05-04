package com.commondb.db.bo;

public class CharacterData
{
  private Integer dataId;
  private String dataName;
  private String dataDesc;
  private Integer charaId;
  private Integer userId;
  private Integer isenabled;
  private Integer isshared;
  private Integer sort;
  private Integer parId;
  private UserSimple user;
  private CharacterDef charaDef;
  
  public Integer getDataId()
  {
    return this.dataId;
  }
  
  public void setDataId(Integer dataId)
  {
    this.dataId = dataId;
  }
  
  public String getDataName()
  {
    return this.dataName;
  }
  
  public void setDataName(String dataName)
  {
    this.dataName = dataName;
  }
  
  public String getDataDesc()
  {
    return this.dataDesc;
  }
  
  public void setDataDesc(String dataDesc)
  {
    this.dataDesc = dataDesc;
  }
  
  public Integer getCharaId()
  {
    return this.charaId;
  }
  
  public void setCharaId(Integer charaId)
  {
    this.charaId = charaId;
  }
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public Integer getIsenabled()
  {
    return this.isenabled;
  }
  
  public void setIsenabled(Integer isenabled)
  {
    this.isenabled = isenabled;
  }
  
  public Integer getIsshared()
  {
    return this.isshared;
  }
  
  public void setIsshared(Integer isshared)
  {
    this.isshared = isshared;
  }
  
  public Integer getSort()
  {
    return this.sort;
  }
  
  public void setSort(Integer sort)
  {
    this.sort = sort;
  }
  
  public Integer getParId()
  {
    return this.parId;
  }
  
  public void setParId(Integer parId)
  {
    this.parId = parId;
  }
  
  public UserSimple getUser()
  {
    return this.user;
  }
  
  public void setUser(UserSimple user)
  {
    this.user = user;
  }
  
  public CharacterDef getCharaDef()
  {
    return this.charaDef;
  }
  
  public void setCharaDef(CharacterDef charaDef)
  {
    this.charaDef = charaDef;
  }
  
  public Integer getId()
  {
    return this.dataId;
  }
}
