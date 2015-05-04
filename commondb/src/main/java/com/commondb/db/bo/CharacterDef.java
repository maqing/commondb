package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.List;

public class CharacterDef
{
  private Integer charaId;
  private String charaName;
  private String charaDesc;
  private Integer checklevel;
  private Integer ischeckmultiple;
  private Integer userId;
  private Integer isenabled;
  private Integer isshared;
  private Integer sort;
  private UserSimple user;
  private List<CharacterData> charaDatalist = new ArrayList();
  
  public Integer getCharaId()
  {
    return this.charaId;
  }
  
  public void setCharaId(Integer charaId)
  {
    this.charaId = charaId;
  }
  
  public String getCharaName()
  {
    return this.charaName;
  }
  
  public void setCharaName(String charaName)
  {
    this.charaName = charaName;
  }
  
  public String getCharaDesc()
  {
    return this.charaDesc;
  }
  
  public void setCharaDesc(String charaDesc)
  {
    this.charaDesc = charaDesc;
  }
  
  public Integer getChecklevel()
  {
    return this.checklevel;
  }
  
  public void setChecklevel(Integer checklevel)
  {
    this.checklevel = checklevel;
  }
  
  public Integer getIscheckmultiple()
  {
    return this.ischeckmultiple;
  }
  
  public void setIscheckmultiple(Integer ischeckmultiple)
  {
    this.ischeckmultiple = ischeckmultiple;
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
  
  public UserSimple getUser()
  {
    return this.user;
  }
  
  public void setUser(UserSimple user)
  {
    this.user = user;
  }
  
  public List<CharacterData> getCharaDatalist()
  {
    return this.charaDatalist;
  }
  
  public void setCharaDatalist(List<CharacterData> charaDatalist)
  {
    this.charaDatalist = charaDatalist;
  }
  
  public Integer getId()
  {
    return this.charaId;
  }
}
