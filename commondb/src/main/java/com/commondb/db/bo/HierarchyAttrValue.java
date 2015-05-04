package com.commondb.db.bo;

public class HierarchyAttrValue
{
  private String valueId;
  private Integer metaId;
  private Integer attrId;
  private String content;
  private String parentId;
  private Integer levelNum;
  
  public String getValueId()
  {
    return this.valueId;
  }
  
  public void setValueId(String valueId)
  {
    this.valueId = valueId;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public Integer getAttrId()
  {
    return this.attrId;
  }
  
  public void setAttrId(Integer attrId)
  {
    this.attrId = attrId;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public String getParentId()
  {
    return this.parentId;
  }
  
  public void setParentId(String parentId)
  {
    this.parentId = parentId;
  }
  
  public Integer getLevelNum()
  {
    return this.levelNum;
  }
  
  public void setLevelNum(Integer levelNum)
  {
    this.levelNum = levelNum;
  }
}
