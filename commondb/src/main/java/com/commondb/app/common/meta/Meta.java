package com.commondb.app.common.meta;

import java.util.List;

public class Meta
{
  private Integer metaId;
  private String metaName;
  private List<IField> fields;
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public String getMetaName()
  {
    return this.metaName;
  }
  
  public void setMetaName(String metaName)
  {
    this.metaName = metaName;
  }
  
  public void setFields(List<IField> fields)
  {
    this.fields = fields;
  }
  
  public List<IField> getFields()
  {
    return this.fields;
  }
}
