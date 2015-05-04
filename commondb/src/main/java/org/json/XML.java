package org.json;

import java.util.Iterator;

public class XML
{
  public static final Character AMP = new Character('&');
  public static final Character APOS = new Character('\'');
  public static final Character BANG = new Character('!');
  public static final Character EQ = new Character('=');
  public static final Character GT = new Character('>');
  public static final Character LT = new Character('<');
  public static final Character QUEST = new Character('?');
  public static final Character QUOT = new Character('"');
  public static final Character SLASH = new Character('/');

  public static String escape(String string)
  {
    StringBuffer sb = new StringBuffer();
    int i = 0;
    for (int len = string.length(); i < len; i++)
    {
      char c = string.charAt(i);
      switch (c)
      {
      case '&':
        sb.append("&amp;");
        break;
      case '<':
        sb.append("&lt;");
        break;
      case '>':
        sb.append("&gt;");
        break;
      case '"':
        sb.append("&quot;");
        break;
      default:
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static void noSpace(String string)
    throws JSONException
  {
    int length = string.length();
    if (length == 0) {
      throw new JSONException("Empty string.");
    }
    for (int i = 0; i < length; i++) {
      if (Character.isWhitespace(string.charAt(i))) {
        throw new JSONException("'" + string +
          "' contains a space character.");
      }
    }
  }

  private static boolean parse(XMLTokener x, JSONObject context, String name)
    throws JSONException
  {
    JSONObject o = null;













    Object t = x.nextToken();
    if (t == BANG)
    {
      char c = x.next();
      if (c == '-')
      {
        if (x.next() == '-')
        {
          x.skipPast("-->");
          return false;
        }
        x.back();
      }
      else if (c == '[')
      {
        t = x.nextToken();
        if ((t.equals("CDATA")) &&
          (x.next() == '['))
        {
          String s = x.nextCDATA();
          if (s.length() > 0) {
            context.accumulate("content", s);
          }
          return false;
        }
        throw x.syntaxError("Expected 'CDATA['");
      }
      int i = 1;
      do
      {
        t = x.nextMeta();
        if (t == null) {
          throw x.syntaxError("Missing '>' after '<!'.");
        }
        if (t == LT) {
          i++;
        } else if (t == GT) {
          i--;
        }
      } while (i > 0);
      return false;
    }
    if (t == QUEST)
    {
      x.skipPast("?>");
      return false;
    }
    if (t == SLASH)
    {
      t = x.nextToken();
      if (name == null) {
        throw x.syntaxError("Mismatched close tag" + t);
      }
      if (!t.equals(name)) {
        throw x.syntaxError("Mismatched " + name + " and " + t);
      }
      if (x.nextToken() != GT) {
        throw x.syntaxError("Misshaped close tag");
      }
      return true;
    }
    if ((t instanceof Character)) {
      throw x.syntaxError("Misshaped tag");
    }
    String n = (String)t;
    t = null;
    o = new JSONObject();
    for (;;)
    {
      if (t == null) {
        t = x.nextToken();
      }
      if (!(t instanceof String)) {
        break;
      }
      String s = (String)t;
      t = x.nextToken();
      if (t == EQ)
      {
        t = x.nextToken();
        if (!(t instanceof String)) {
          throw x.syntaxError("Missing value");
        }
        o.accumulate(s, JSONObject.stringToValue((String)t));
        t = null;
      }
      else
      {
        o.accumulate(s, "");
      }
    }
    if (t == SLASH)
    {
      if (x.nextToken() != GT) {
        throw x.syntaxError("Misshaped tag");
      }
      context.accumulate(n, o);
      return false;
    }
    if (t == GT)
    {
      do
      {
        for (;;)
        {
          t = x.nextContent();
          if (t == null)
          {
            if (n != null) {
              throw x.syntaxError("Unclosed tag " + n);
            }
            return false;
          }
          if (!(t instanceof String)) {
            break;
          }
          String s = (String)t;
          if (s.length() > 0) {
            o.accumulate("content", JSONObject.stringToValue(s));
          }
        }
      } while ((t != LT) ||
        (!parse(x, o, n)));
      if (o.length() == 0) {
        context.accumulate(n, "");
      } else if ((o.length() == 1) &&
        (o.opt("content") != null)) {
        context.accumulate(n, o.opt("content"));
      } else {
        context.accumulate(n, o);
      }
      return false;
    }
    throw x.syntaxError("Misshaped tag");
  }

  public static JSONObject toJSONObject(String string)
    throws JSONException
  {
    JSONObject o = new JSONObject();
    XMLTokener x = new XMLTokener(string);
    while ((x.more()) && (x.skipPast("<"))) {
      parse(x, o, null);
    }
    return o;
  }

  public static String toString(Object o)
    throws JSONException
  {
    return toString(o, null);
  }

  public static String toString(Object o, String tagName)
    throws JSONException
  {
    StringBuffer b = new StringBuffer();
    if ((o instanceof JSONObject))
    {
      if (tagName != null)
      {
        b.append('<');
        b.append(tagName);
        b.append('>');
      }
      JSONObject jo = (JSONObject)o;
      Iterator keys = jo.keys();
      while (keys.hasNext())
      {
        String k = keys.next().toString();
        Object v = jo.opt(k);
        if (v == null) {
          v = "";
        }
        String s;
        if ((v instanceof String)) {
          s = (String)v;
        } else {
          s = null;
        }
        if (k.equals("content"))
        {
          if ((v instanceof JSONArray))
          {
            JSONArray ja = (JSONArray)v;
            int len = ja.length();
            for (int i = 0; i < len; i++)
            {
              if (i > 0) {
                b.append('\n');
              }
              b.append(escape(ja.get(i).toString()));
            }
          }
          else
          {
            b.append(escape(v.toString()));
          }
        }
        else if ((v instanceof JSONArray))
        {
          JSONArray ja = (JSONArray)v;
          int len = ja.length();
          for (int i = 0; i < len; i++)
          {
            v = ja.get(i);
            if ((v instanceof JSONArray))
            {
              b.append('<');
              b.append(k);
              b.append('>');
              b.append(toString(v));
              b.append("</");
              b.append(k);
              b.append('>');
            }
            else
            {
              b.append(toString(v, k));
            }
          }
        }
        else if (v.equals(""))
        {
          b.append('<');
          b.append(k);
          b.append("/>");
        }
        else
        {
          b.append(toString(v, k));
        }
      }
      if (tagName != null)
      {
        b.append("</");
        b.append(tagName);
        b.append('>');
      }
      return b.toString();
    }
    if ((o instanceof JSONArray))
    {
      JSONArray ja = (JSONArray)o;
      int len = ja.length();
      for (int i = 0; i < len; i++)
      {
        Object v = ja.opt(i);
        b.append(toString(v, tagName == null ? "array" : tagName));
      }
      return b.toString();
    }
    String s = o == null ? "null" : escape(o.toString());
    return

      "<" + tagName + ">" + s + "</" + tagName + ">";
  }
}
