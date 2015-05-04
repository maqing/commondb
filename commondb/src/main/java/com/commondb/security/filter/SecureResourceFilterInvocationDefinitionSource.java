package com.commondb.security.filter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.RegexUrlPathMatcher;
import org.springframework.security.util.UrlMatcher;

public class SecureResourceFilterInvocationDefinitionSource
  implements FilterInvocationDefinitionSource, InitializingBean
{
  private UrlMatcher urlMatcher;
  private boolean useAntPath = true;
  private boolean lowercaseComparisons = true;
  
  public void setUseAntPath(boolean useAntPath)
  {
    this.useAntPath = useAntPath;
  }
  
  public void setLowercaseComparisons(boolean lowercaseComparisons)
  {
    this.lowercaseComparisons = lowercaseComparisons;
  }
  
  public void afterPropertiesSet()
    throws Exception
  {
    this.urlMatcher = new RegexUrlPathMatcher();
    if (this.useAntPath) {
      this.urlMatcher = new AntUrlPathMatcher();
    }
    if ("true".equals(Boolean.valueOf(this.lowercaseComparisons)))
    {
      if (!this.useAntPath) {
        ((RegexUrlPathMatcher)this.urlMatcher).setRequiresLowerCaseUrl(true);
      }
    }
    else if (("false".equals(Boolean.valueOf(this.lowercaseComparisons))) && 
      (this.useAntPath)) {
      ((AntUrlPathMatcher)this.urlMatcher).setRequiresLowerCaseUrl(false);
    }
  }
  
  public ConfigAttributeDefinition getAttributes(Object filter)
    throws IllegalArgumentException
  {
    FilterInvocation filterInvocation = (FilterInvocation)filter;
    String requestURI = filterInvocation.getRequestUrl();
    Map<String, String> urlAuthorities = getUrlAuthorities(filterInvocation);
    
    String grantedAuthorities = null;
    for (Iterator<Map.Entry<String, String>> iter = urlAuthorities.entrySet().iterator(); iter.hasNext();)
    {
      Map.Entry<String, String> entry = (Map.Entry)iter.next();
      String url = (String)entry.getKey();
      if (this.urlMatcher.pathMatchesUrl(url, requestURI))
      {
        grantedAuthorities = (String)entry.getValue();
        break;
      }
    }
    if (grantedAuthorities != null)
    {
      ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
      configAttrEditor.setAsText(grantedAuthorities);
      return (ConfigAttributeDefinition)configAttrEditor.getValue();
    }
    return null;
  }
  
  public Collection getConfigAttributeDefinitions()
  {
    return null;
  }
  
  public boolean supports(Class clazz)
  {
    return true;
  }
  
  private Map<String, String> getUrlAuthorities(FilterInvocation filterInvocation)
  {
    ServletContext servletContext = filterInvocation.getHttpRequest().getSession().getServletContext();
    return (Map)servletContext.getAttribute("urlAuthorities");
  }
}
