package com.commondb.db.web;

import com.commondb.common.JsonResult;
import com.commondb.db.bo.CharacterData;
import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.User;
import com.commondb.db.service.MetaService;
import com.commondb.security.service.SecurityUserHolder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.ArrayList;
import java.util.List;

public class CharacterAttrAction
  extends ActionSupport
  implements Preparable
{
  private JsonResult result = new JsonResult();
  private MetaService metaService;
  private Integer metaId;
  private String entityId;
  private Integer dataId;
  private Integer charaId;
  private Integer[] dataIdArr;
  private Integer[] charaIdArr;
  private Integer ischeckmultiple;
  private Integer isshared;
  private String charaName;
  private String charaDesc;
  private String dataName;
  private String dataDesc;
  private String data;
  private String[] charaArr;
  
  public String[] getCharaArr()
  {
    return this.charaArr;
  }
  
  public void setCharaArr(String[] charaArr)
  {
    this.charaArr = charaArr;
  }
  
  public String getData()
  {
    return this.data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public Integer getIsshared()
  {
    return this.isshared;
  }
  
  public void setIsshared(Integer isshared)
  {
    this.isshared = isshared;
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
  
  public Integer getDataId()
  {
    return this.dataId;
  }
  
  public void setDataId(Integer dataId)
  {
    this.dataId = dataId;
  }
  
  public Integer getCharaId()
  {
    return this.charaId;
  }
  
  public void setCharaId(Integer charaId)
  {
    this.charaId = charaId;
  }
  
  public Integer[] getDataIdArr()
  {
    return this.dataIdArr;
  }
  
  public void setDataIdArr(Integer[] dataIdArr)
  {
    this.dataIdArr = dataIdArr;
  }
  
  public Integer[] getCharaIdArr()
  {
    return this.charaIdArr;
  }
  
  public void setCharaIdArr(Integer[] charaIdArr)
  {
    this.charaIdArr = charaIdArr;
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
  
  public Integer getIscheckmultiple()
  {
    return this.ischeckmultiple;
  }
  
  public void setIscheckmultiple(Integer ischeckmultiple)
  {
    this.ischeckmultiple = ischeckmultiple;
  }
  
  public void prepare()
    throws Exception
  {}
  
  public MetaService getMetaService()
  {
    return this.metaService;
  }
  
  public void setMetaService(MetaService metaService)
  {
    this.metaService = metaService;
  }
  
  public JsonResult getResult()
  {
    return this.result;
  }
  
  public void setResult(JsonResult result)
  {
    this.result = result;
  }
  
  public String createCharaDef()
  {
    this.result = new JsonResult();
    try
    {
      CharacterDef characterDef = new CharacterDef();
      characterDef.setCharaName(this.charaName);
      characterDef.setCharaDesc(this.charaDesc);
      characterDef.setIscheckmultiple(this.ischeckmultiple);
      characterDef.setIsshared(this.isshared);
      characterDef.setUserId(SecurityUserHolder.getCurrentUser().getUserId());
      characterDef.setCharaId(this.metaService.createCharaDef(characterDef));
      
      this.result.success = true;
      this.result.setData(characterDef);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String createCharaDefWithMeta()
  {
    this.result = new JsonResult();
    try
    {
      CharacterDef characterDef = new CharacterDef();
      characterDef.setCharaName(this.charaName);
      characterDef.setCharaDesc(this.charaDesc);
      characterDef.setIscheckmultiple(this.ischeckmultiple);
      characterDef.setIsshared(this.isshared);
      characterDef.setUserId(SecurityUserHolder.getCurrentUser().getUserId());
      characterDef.setCharaId(this.metaService.createCharaDefWithMeta(characterDef, this.metaId));
      
      this.result.success = true;
      this.result.setData(characterDef);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String dropCharaDef()
  {
    this.result = new JsonResult();
    try
    {
      List<Integer> charaIdL = new ArrayList();
      charaIdL.add(this.charaId);
      CharacterDef characterDef = (CharacterDef)this.metaService.listCharaDefById(charaIdL).get(0);
      this.metaService.delCharaDef(characterDef);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updateCharaDef()
  {
    this.result = new JsonResult();
    try
    {
      CharacterDef characterDef = new CharacterDef();
      characterDef.setCharaId(this.charaId);
      characterDef.setCharaName(this.charaName);
      characterDef.setCharaDesc(this.charaDesc);
      characterDef.setIscheckmultiple(this.ischeckmultiple);
      characterDef.setIsshared(this.isshared);
      characterDef.setUserId(SecurityUserHolder.getCurrentUser().getUserId());
      
      this.metaService.updateCharaDef(characterDef);
      
      this.result.success = true;
      this.result.setData(characterDef);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String listCharaDef()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.listCharaDef());
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String listCharaDefByMeta()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.listCharaDefByMeta(this.metaId));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String createCharaData()
  {
    this.result = new JsonResult();
    try
    {
      CharacterData characterData = new CharacterData();
      characterData.setDataName(this.dataName);
      characterData.setDataDesc(this.dataDesc);
      characterData.setCharaId(this.charaId);
      characterData.setIsshared(this.isshared);
      characterData.setUserId(SecurityUserHolder.getCurrentUser().getUserId());
      characterData.setDataId(this.metaService.createCharaData(characterData));
      
      this.result.success = true;
      this.result.setData(characterData);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String createCharaDataWithEntity()
  {
    this.result = new JsonResult();
    try
    {
      CharacterData characterData = new CharacterData();
      characterData.setDataName(this.dataName);
      characterData.setDataDesc(this.dataDesc);
      characterData.setCharaId(this.charaId);
      characterData.setIsshared(this.isshared);
      characterData.setUserId(SecurityUserHolder.getCurrentUser().getUserId());
      characterData.setDataId(this.metaService.createCharaDataWithEntity(characterData, this.metaId, this.entityId));
      
      this.result.success = true;
      this.result.setData(characterData);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String dropCharaData()
  {
    this.result = new JsonResult();
    try
    {
      List<Integer> dataIdL = new ArrayList();
      dataIdL.add(this.dataId);
      CharacterData characterData = (CharacterData)this.metaService.listCharaDataById(dataIdL).get(0);
      this.metaService.delCharaData(characterData);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updateCharaData()
  {
    this.result = new JsonResult();
    try
    {
      CharacterData characterData = new CharacterData();
      characterData.setDataId(this.dataId);
      characterData.setDataName(this.dataName);
      characterData.setDataDesc(this.dataDesc);
      characterData.setCharaId(this.charaId);
      characterData.setIsshared(this.isshared);
      characterData.setUserId(SecurityUserHolder.getCurrentUser().getUserId());
      
      this.metaService.updateCharaData(characterData);
      
      this.result.success = true;
      this.result.setData(characterData);
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String listCharaData()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.listCharaData(this.charaId));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String listCharaDataByEntity()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.listCharaDataByEntity(this.metaId, this.entityId));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String listCharaDataByCharaId()
  {
    this.result = new JsonResult();
    try
    {
      this.result.setData(this.metaService.listCharaData(this.charaIdArr));
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String createRMetaChara()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.createRMetaChara(this.metaId, this.charaIdArr);
      
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String dropRMetaChara()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.delRMetaChara(this.metaId, this.charaIdArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updateRMetaChara()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.updateRMetaChara(this.metaId, this.charaIdArr);
      
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String createREntityCharaData()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.createREntityCharaData(this.metaId, this.entityId, this.charaId, this.dataIdArr);
      
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String dropREntityCharaData()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.delREntityCharaData(this.metaId, this.entityId, this.dataIdArr);
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updateREntityCharaData()
  {
    this.result = new JsonResult();
    try
    {
      this.metaService.updateREntityCharaData(this.metaId, this.entityId, this.charaId, this.dataIdArr);
      
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
  
  public String updateREntityCharaDataArr()
  {
    this.result = new JsonResult();
    try
    {
      if (this.charaArr != null)
      {
        Integer[][] charaArrI = new Integer[this.charaArr.length][2];
        for (int i = 0; i < this.charaArr.length; i++)
        {
          String[] s = this.charaArr[i].split(",");
          charaArrI[i][0] = Integer.valueOf(Integer.parseInt(s[0]));
          charaArrI[i][1] = Integer.valueOf(Integer.parseInt(s[1]));
        }
        this.metaService.updateREntityCharaDataArr(this.metaId, this.entityId, charaArrI);
      }
      else
      {
        Integer[][] charaArrI = (Integer[][])null;
        this.metaService.updateREntityCharaDataArr(this.metaId, this.entityId, charaArrI);
      }
      this.result.success = true;
    }
    catch (Throwable t)
    {
      this.result.success = false;
      this.result.errormsg = t.getMessage();
      t.printStackTrace();
    }
    return "success";
  }
}
