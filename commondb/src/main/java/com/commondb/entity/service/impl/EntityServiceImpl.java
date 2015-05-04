package com.commondb.entity.service.impl;

import com.commondb.db.bo.Entity;
import com.commondb.db.dao.CharacterAttrDataDAO;
import com.commondb.db.dao.CharacterAttrDefDAO;
import com.commondb.db.dao.CharacterAttrValueDAO;
import com.commondb.db.dao.DescAttrDataDAO;
import com.commondb.db.dao.DescAttrDefDAO;
import com.commondb.db.dao.EntityDAO;
import com.commondb.db.dao.HierarchyAttrDataDAO;
import com.commondb.db.dao.HierarchyAttrDefDAO;
import com.commondb.db.dao.MetaDAO;
import com.commondb.db.dao.PicAttrDataDAO;
import com.commondb.db.dao.PicAttrDefDAO;
import java.util.Date;
import java.util.Map;

public class EntityServiceImpl
{
  private MetaDAO metaDAO;
  private CharacterAttrDefDAO characterAttrDefDAO;
  private HierarchyAttrDefDAO hierarchyAttrDefDAO;
  private PicAttrDefDAO picAttrDefDAO;
  private DescAttrDefDAO descAttrDefDAO;
  private EntityDAO entityDAO;
  private PicAttrDataDAO picAttrDataDAO;
  private DescAttrDataDAO descAttrDataDAO;
  private HierarchyAttrDataDAO hierarchyAttrDataDAO;
  private CharacterAttrDataDAO characterAttrDataDAO;
  private CharacterAttrValueDAO characterAttrValueDAO;
  
  public void saveEntity(Map content)
  {
    Entity entity = new Entity();
    entity.setCreateTime(new Date());
    entity.setMetaId(new Integer((String)content.get("metaId")));
    Integer entityId = this.entityDAO.insert(entity);
  }
  
  public EntityDAO getEntityDAO()
  {
    return this.entityDAO;
  }
  
  public void setEntityDAO(EntityDAO entityDAO)
  {
    this.entityDAO = entityDAO;
  }
  
  public PicAttrDataDAO getPicAttrDataDAO()
  {
    return this.picAttrDataDAO;
  }
  
  public void setPicAttrDataDAO(PicAttrDataDAO picAttrDataDAO)
  {
    this.picAttrDataDAO = picAttrDataDAO;
  }
  
  public DescAttrDataDAO getDescAttrDataDAO()
  {
    return this.descAttrDataDAO;
  }
  
  public void setDescAttrDataDAO(DescAttrDataDAO descAttrDataDAO)
  {
    this.descAttrDataDAO = descAttrDataDAO;
  }
  
  public HierarchyAttrDataDAO getHierarchyAttrDataDAO()
  {
    return this.hierarchyAttrDataDAO;
  }
  
  public void setHierarchyAttrDataDAO(HierarchyAttrDataDAO hierarchyAttrDataDAO)
  {
    this.hierarchyAttrDataDAO = hierarchyAttrDataDAO;
  }
  
  public CharacterAttrDataDAO getCharacterAttrDataDAO()
  {
    return this.characterAttrDataDAO;
  }
  
  public void setCharacterAttrDataDAO(CharacterAttrDataDAO characterAttrDataDAO)
  {
    this.characterAttrDataDAO = characterAttrDataDAO;
  }
  
  public CharacterAttrValueDAO getCharacterAttrValueDAO()
  {
    return this.characterAttrValueDAO;
  }
  
  public void setCharacterAttrValueDAO(CharacterAttrValueDAO characterAttrValueDAO)
  {
    this.characterAttrValueDAO = characterAttrValueDAO;
  }
  
  public void game() {}
  
  public MetaDAO getMetaDAO()
  {
    return this.metaDAO;
  }
  
  public void setMetaDAO(MetaDAO metaDAO)
  {
    this.metaDAO = metaDAO;
  }
  
  public CharacterAttrDefDAO getCharacterAttrDefDAO()
  {
    return this.characterAttrDefDAO;
  }
  
  public void setCharacterAttrDefDAO(CharacterAttrDefDAO characterAttrDefDAO)
  {
    this.characterAttrDefDAO = characterAttrDefDAO;
  }
  
  public HierarchyAttrDefDAO getHierarchyAttrDefDAO()
  {
    return this.hierarchyAttrDefDAO;
  }
  
  public void setHierarchyAttrDefDAO(HierarchyAttrDefDAO hierarchyAttrDefDAO)
  {
    this.hierarchyAttrDefDAO = hierarchyAttrDefDAO;
  }
  
  public PicAttrDefDAO getPicAttrDefDAO()
  {
    return this.picAttrDefDAO;
  }
  
  public void setPicAttrDefDAO(PicAttrDefDAO picAttrDefDAO)
  {
    this.picAttrDefDAO = picAttrDefDAO;
  }
  
  public DescAttrDefDAO getDescAttrDefDAO()
  {
    return this.descAttrDefDAO;
  }
  
  public void setDescAttrDefDAO(DescAttrDefDAO descAttrDefDAO)
  {
    this.descAttrDefDAO = descAttrDefDAO;
  }
}
