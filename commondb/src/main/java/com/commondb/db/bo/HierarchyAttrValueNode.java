package com.commondb.db.bo;

import java.util.ArrayList;
import java.util.List;

public class HierarchyAttrValueNode
{
  private HierarchyAttrValue hierarchyAttrValue;
  private List<HierarchyAttrValueNode> cNodes;
  
  public void addChild(HierarchyAttrValueNode node)
  {
    if (this.cNodes == null) {
      this.cNodes = new ArrayList();
    }
    this.cNodes.add(node);
  }
  
  public HierarchyAttrValue getHierarchyAttrValue()
  {
    return this.hierarchyAttrValue;
  }
  
  public void setHierarchyAttrValue(HierarchyAttrValue hierarchyAttrValue)
  {
    this.hierarchyAttrValue = hierarchyAttrValue;
  }
  
  public List<HierarchyAttrValueNode> getCNodes()
  {
    return this.cNodes;
  }
  
  public void setCNodes(List<HierarchyAttrValueNode> nodes)
  {
    this.cNodes = nodes;
  }
}
