package com.commondb.db.dao;

import com.commondb.db.bo.REntityAttachment;
import com.commondb.db.bo.REntityAttachmentCriteria;
import java.sql.SQLException;
import java.util.List;

public abstract interface REntityAttachmentDAO
{
  public abstract void insert(REntityAttachment paramREntityAttachment);
  
  public abstract int updateByPrimaryKey(REntityAttachment paramREntityAttachment);
  
  public abstract int updateByPrimaryKeySelective(REntityAttachment paramREntityAttachment);
  
  public abstract List selectByExample(REntityAttachmentCriteria paramREntityAttachmentCriteria);
  
  public abstract REntityAttachment selectByPrimaryKey(String paramString);
  
  public abstract int deleteByExample(REntityAttachmentCriteria paramREntityAttachmentCriteria);
  
  public abstract int deleteByPrimaryKey(String paramString)
    throws SQLException;
  
  public abstract int countByExample(REntityAttachmentCriteria paramREntityAttachmentCriteria);
  
  public abstract int updateByExampleSelective(REntityAttachment paramREntityAttachment, REntityAttachmentCriteria paramREntityAttachmentCriteria);
  
  public abstract int updateByExample(REntityAttachment paramREntityAttachment, REntityAttachmentCriteria paramREntityAttachmentCriteria);
  
  public abstract List getRAttachmentById(Integer paramInteger, String paramString);
}
