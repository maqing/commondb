package com.commondb.app.common.meta;

import com.commondb.db.bo.CharacterDef;
import com.commondb.db.bo.DescAttrDef;
import com.commondb.db.bo.HierarchyAttrDef;
import com.commondb.db.bo.PicAttrDef;

public class FieldFactory
{
  private final int DATETIME = 1;
  private final int STRING = 2;
  private final int CONTENT = 3;
  private final int NUM = 4;
  private final int MONEY = 5;
  private final int BOOLEAN = 6;
  private final int URL = 7;
  private static FieldFactory instance = new FieldFactory();
  
  public static FieldFactory getInstance()
  {
    return instance;
  }
  
  public IField createField(Object fieldDef)
  {
    if ((fieldDef instanceof PicAttrDef)) {
      return new PicField((PicAttrDef)fieldDef);
    }
    if ((fieldDef instanceof DescAttrDef))
    {
      DescAttrDef df = (DescAttrDef)fieldDef;
      if (df.getPropertyId().intValue() == 1) {
        return new DatetimeField((DescAttrDef)fieldDef);
      }
      if (df.getPropertyId().intValue() == 2) {
        return new DescField((DescAttrDef)fieldDef);
      }
      if (df.getPropertyId().intValue() == 3) {
        return new ContentField((DescAttrDef)fieldDef);
      }
      if (df.getPropertyId().intValue() == 4) {
        return new NumberField((DescAttrDef)fieldDef);
      }
      if (df.getPropertyId().intValue() == 5) {
        return new MoneyField((DescAttrDef)fieldDef);
      }
      if (df.getPropertyId().intValue() == 6) {
        return new BooleanField((DescAttrDef)fieldDef);
      }
      if (df.getPropertyId().intValue() == 7) {
        return new UrlField((DescAttrDef)fieldDef);
      }
      return null;
    }
    if ((fieldDef instanceof CharacterDef))
    {
      CharacterDef cFieldDef = (CharacterDef)fieldDef;
      if (cFieldDef.getIscheckmultiple().intValue() == 1) {
        return new CharacterField(cFieldDef);
      }
      return new SingleCharacterField(cFieldDef);
    }
    if ((fieldDef instanceof HierarchyAttrDef)) {
      return new HierarchyField((HierarchyAttrDef)fieldDef);
    }
    if (fieldDef == null) {
      return new IdField();
    }
    return null;
  }
}
