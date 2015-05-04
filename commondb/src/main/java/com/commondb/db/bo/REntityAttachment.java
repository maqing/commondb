package com.commondb.db.bo;

public class REntityAttachment
{
  private String id;
  private Integer metaId;
  private String entityId;
  private String attachmentName;
  private String attachmentPath;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public Integer getMetaId()
  {
    return this.metaId;
  }
  
  public void setMetaId(Integer metaId)
  {
    this.metaId = metaId;
  }
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  public String getAttachmentName()
  {
    return this.attachmentName.replaceAll("\\s*|\t|\r|\n", "");
  }
  
  public void setAttachmentName(String attachmentName)
  {
    this.attachmentName = attachmentName.replaceAll("\\s*|\t|\r|\n", "");
  }
  
  public String getAttachmentPath()
  {
    return this.attachmentPath.replaceAll("\\s*|\t|\r|\n", "");
  }
  
  public void setAttachmentPath(String attachmentPath)
  {
    this.attachmentPath = attachmentPath.replaceAll("\\s*|\t|\r|\n", "");
  }
}
