package com.commondb.db.bo;

import java.util.Date;

public class OperationBox
{
  private String id;
  private String label;
  private Integer metaId;
  private String entityId;
  private String operationType;
  private Date createTime;
  private String createUser;
  private String attachmentPath;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getLabel()
  {
    return this.label.replaceAll("\\s*|\t|\r|\n", "");
  }
  
  public void setLabel(String label)
  {
    this.label = label.replaceAll("\\s*|\t|\r|\n", "");
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
  
  public String getOperationType()
  {
    return this.operationType;
  }
  
  public void setOperationType(String operationType)
  {
    this.operationType = operationType;
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public String getCreateUser()
  {
    return this.createUser;
  }
  
  public void setCreateUser(String createUser)
  {
    this.createUser = createUser;
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
