package org.json;

public class CDL
{
  private static String getValue(JSONTokener x)
    throws JSONException
  {
    char c;
    do
    {
      c = x.next();
    } while ((c == ' ') || (c == '\t'));
    switch (c)
    {
    case '\000': 
      return null;
    case '"': 
    case '\'': 
      char q = c;
      StringBuffer sb = new StringBuffer();
      for (;;)
      {
        c = x.next();
        if (c == q) {
          break;
        }
        if ((c == 0) || (c == '\n') || (c == '\r')) {
          throw x.syntaxError("Missing close quote '" + q + "'.");
        }
        sb.append(c);
      }
      return sb.toString();
    case ',': 
      x.back();
      return "";
    }
    x.back();
    return x.nextTo(',');
  }
  
  public static JSONArray rowToJSONArray(JSONTokener x)
    throws JSONException
  {
    JSONArray ja = new JSONArray();
    
    String value = getValue(x);
    if ((value == null) || ((ja.length() == 0) && (value.length() == 0))) {
      return null;
    }
    ja.put(value);
    char c;
    do
    {
      c = x.next();
      if (c == ',') {
        break;
      }
    } while (c == ' ');
    if ((c == '\n') || (c == '\r') || (c == 0)) {
      return ja;
    }
    throw x.syntaxError("Bad character '" + c + "' (" + 
      c + ").");
  }
  
  public static JSONObject rowToJSONObject(JSONArray names, JSONTokener x)
    throws JSONException
  {
    JSONArray ja = rowToJSONArray(x);
    return ja != null ? ja.toJSONObject(names) : null;
  }
  
  public static JSONArray toJSONArray(String string)
    throws JSONException
  {
    return toJSONArray(new JSONTokener(string));
  }
  
  public static JSONArray toJSONArray(JSONTokener x)
    throws JSONException
  {
    return toJSONArray(rowToJSONArray(x), x);
  }
  
  public static JSONArray toJSONArray(JSONArray names, String string)
    throws JSONException
  {
    return toJSONArray(names, new JSONTokener(string));
  }
  
  public static JSONArray toJSONArray(JSONArray names, JSONTokener x)
    throws JSONException
  {
    if ((names == null) || (names.length() == 0)) {
      return null;
    }
    JSONArray ja = new JSONArray();
    for (;;)
    {
      JSONObject jo = rowToJSONObject(names, x);
      if (jo == null) {
        break;
      }
      ja.put(jo);
    }
    if (ja.length() == 0) {
      return null;
    }
    return ja;
  }
  
  public static String rowToString(JSONArray ja)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < ja.length(); i++)
    {
      if (i > 0) {
        sb.append(',');
      }
      Object o = ja.opt(i);
      if (o != null)
      {
        String s = o.toString();
        if ((s.indexOf(',') >= 0) || (s.indexOf('\n') >= 0) || 
          (s.indexOf('\r') >= 0) || (s.indexOf(0) >= 0) || 
          (s.charAt(0) == '"'))
        {
          sb.append('"');
          int length = s.length();
          for (int j = 0; j < length; j++)
          {
            char c = s.charAt(j);
            if ((c >= ' ') && (c != '"')) {
              sb.append(c);
            }
          }
          sb.append('"');
        }
        else
        {
          sb.append(s);
        }
      }
    }
    sb.append('\n');
    return sb.toString();
  }
  
  public static String toString(JSONArray ja)
    throws JSONException
  {
    JSONObject jo = ja.optJSONObject(0);
    if (jo != null)
    {
      JSONArray names = jo.names();
      if (names != null) {
        return rowToString(names) + toString(names, ja);
      }
    }
    return null;
  }
  
  public static String toString(JSONArray names, JSONArray ja)
    throws JSONException
  {
    if ((names == null) || (names.length() == 0)) {
      return null;
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < ja.length(); i++)
    {
      JSONObject jo = ja.optJSONObject(i);
      if (jo != null) {
        sb.append(rowToString(jo.toJSONArray(names)));
      }
    }
    return sb.toString();
  }
}
