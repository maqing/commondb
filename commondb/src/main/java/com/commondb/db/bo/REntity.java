package com.commondb.db.bo;

public class REntity
{
  private String id;
  private Integer meta1Id;
  private String entity1Id;
  private Integer meta2Id;
  private String entity2Id;
  private String label;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public Integer getMeta1Id()
  {
    return this.meta1Id;
  }
  
  public void setMeta1Id(Integer meta1Id)
  {
    this.meta1Id = meta1Id;
  }
  
  public String getEntity1Id()
  {
    return this.entity1Id;
  }
  
  public void setEntity1Id(String entity1Id)
  {
    this.entity1Id = entity1Id;
  }
  
  public Integer getMeta2Id()
  {
    return this.meta2Id;
  }
  
  public void setMeta2Id(Integer meta2Id)
  {
    this.meta2Id = meta2Id;
  }
  
  public String getEntity2Id()
  {
    return this.entity2Id;
  }
  
  public void setEntity2Id(String entity2Id)
  {
    this.entity2Id = entity2Id;
  }
  
  public String getLabel()
  {
    return this.label.replaceAll("\\s*|\t|\r|\n", "");
  }
  
  public void setLabel(String label)
  {
    this.label = label.replaceAll("\\s*|\t|\r|\n", "");
  }
}
