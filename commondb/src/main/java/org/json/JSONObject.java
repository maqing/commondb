package org.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class JSONObject
{
  private Map map;

  private static final class Null
  {
    protected final Object clone()
    {
      return this;
    }

    public boolean equals(Object object)
    {
      return (object == null) || (object == this);
    }

    public String toString()
    {
      return "null";
    }
  }

  public static final Object NULL = new Null();

  public JSONObject()
  {
    this.map = new HashMap();
  }

  public JSONObject(JSONObject jo, String[] names)
    throws JSONException
  {
    this();
    for (int i = 0; i < names.length; i++) {
      putOnce(names[i], jo.opt(names[i]));
    }
  }

  /***
   * Construct a JSONObject from a JSONTokener.
   * @param x A JSONTokener object containing the source string.
   * @throws JSONException If there is a syntax error in the source string
   *  or a duplicated key.
   */
  public JSONObject(final JSONTokener x) throws JSONException
  {
     this();
     char c;
     String key;

     if (x.nextClean() != '{')
     {
        throw x.syntaxError("A JSONObject text must begin with '{'");
     }
     for (;;)
     {
        c = x.nextClean();
        switch (c)
        {
           case 0:
              throw x.syntaxError("A JSONObject text must end with '}'");
           case '}':
              return;
           default:
              x.back();
              key = x.nextValue().toString();
        }

        /**
         * The key is followed by ':'. We will also tolerate '=' or '=>'.
         */

        c = x.nextClean();
        if (c == '=')
        {
           if (x.next() != '>')
           {
              x.back();
           }
        }
        else if (c != ':')
        {
           throw x.syntaxError("Expected a ':' after a key");
        }
        putOnce(key, x.nextValue());

        /**
         * Pairs are separated by ','. We will also tolerate ';'.
         */

        switch (x.nextClean())
        {
           case ';':
           case ',':
              if (x.nextClean() == '}')
              {
                 return;
              }
              x.back();
              break;
           case '}':
              return;
           default:
              throw x.syntaxError("Expected a ',' or '}'");
        }
     }
  }
  public JSONObject(Map map)
  {
    this.map = (map == null ? new HashMap() : map);
  }

  public JSONObject(Map map, boolean includeSuperClass)
  {
    this.map = new HashMap();
    if (map != null)
    {
      Iterator i = map.entrySet().iterator();
      while (i.hasNext())
      {
        Map.Entry e = (Map.Entry)i.next();
        if (isStandardProperty(e.getValue().getClass())) {
          this.map.put(e.getKey(), e.getValue());
        } else {
          this.map.put(e.getKey(), new JSONObject(e.getValue(),
            includeSuperClass));
        }
      }
    }
  }

  public JSONObject(Object bean)
  {
    this();
    populateInternalMap(bean, false);
  }

  public JSONObject(Object bean, boolean includeSuperClass)
  {
    this();
    populateInternalMap(bean, includeSuperClass);
  }

  private void populateInternalMap(Object bean, boolean includeSuperClass)
  {
    Class klass = bean.getClass();
    if (klass.getClassLoader() == null) {
      includeSuperClass = false;
    }
    Method[] methods = includeSuperClass ?
      klass.getMethods() : klass.getDeclaredMethods();
    for (int i = 0; i < methods.length; i++) {
      try
      {
        Method method = methods[i];
        if (Modifier.isPublic(method.getModifiers()))
        {
          String name = method.getName();
          String key = "";
          if (name.startsWith("get")) {
            key = name.substring(3);
          } else if (name.startsWith("is")) {
            key = name.substring(2);
          }
          if ((key.length() > 0) &&
            (Character.isUpperCase(key.charAt(0))) &&
            (method.getParameterTypes().length == 0))
          {
            if (key.length() == 1) {
              key = key.toLowerCase();
            } else if (!Character.isUpperCase(key.charAt(1))) {
              key =
                key.substring(0, 1).toLowerCase() + key.substring(1);
            }
            Object result = method.invoke(bean, null);
            if (result == null) {
              this.map.put(key, NULL);
            } else if (result.getClass().isArray()) {
              this.map.put(key, new JSONArray(result, includeSuperClass));
            } else if ((result instanceof Collection)) {
              this.map.put(key, new JSONArray((Collection)result, includeSuperClass));
            } else if ((result instanceof Map)) {
              this.map.put(key, new JSONObject((Map)result, includeSuperClass));
            } else if (isStandardProperty(result.getClass())) {
              this.map.put(key, result);
            } else if ((result.getClass().getPackage().getName().startsWith("java")) ||
              (result.getClass().getClassLoader() == null)) {
              this.map.put(key, result.toString());
            } else {
              this.map.put(key, new JSONObject(result, includeSuperClass));
            }
          }
        }
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    }
  }

  static boolean isStandardProperty(Class clazz)
  {
    return (clazz.isPrimitive()) || (clazz.isAssignableFrom(Byte.class)) || (clazz.isAssignableFrom(Short.class)) || (clazz.isAssignableFrom(Integer.class)) || (clazz.isAssignableFrom(Long.class)) || (clazz.isAssignableFrom(Float.class)) || (clazz.isAssignableFrom(Double.class)) || (clazz.isAssignableFrom(Character.class)) || (clazz.isAssignableFrom(String.class)) || (clazz.isAssignableFrom(Boolean.class));
  }

  public JSONObject(Object object, String[] names)
  {
    this();
    Class c = object.getClass();
    for (int i = 0; i < names.length; i++)
    {
      String name = names[i];
      try
      {
        putOpt(name, c.getField(name).get(object));
      }
      catch (Exception localException) {}
    }
  }

  public JSONObject(String source)
    throws JSONException
  {
    this(new JSONTokener(source));
  }

  public JSONObject accumulate(String key, Object value)
    throws JSONException
  {
    testValidity(value);
    Object o = opt(key);
    if (o == null) {
      put(key, (value instanceof JSONArray) ?
        new JSONArray().put(value) :
        value);
    } else if ((o instanceof JSONArray)) {
      ((JSONArray)o).put(value);
    } else {
      put(key, new JSONArray().put(o).put(value));
    }
    return this;
  }

  public JSONObject append(String key, Object value)
    throws JSONException
  {
    testValidity(value);
    Object o = opt(key);
    if (o == null) {
      put(key, new JSONArray().put(value));
    } else if ((o instanceof JSONArray)) {
      put(key, ((JSONArray)o).put(value));
    } else {
      throw new JSONException("JSONObject[" + key +
        "] is not a JSONArray.");
    }
    return this;
  }

  public static String doubleToString(double d)
  {
    if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
      return "null";
    }
    String s = Double.toString(d);
    if ((s.indexOf('.') > 0) && (s.indexOf('e') < 0) && (s.indexOf('E') < 0))
    {
      while (s.endsWith("0")) {
        s = s.substring(0, s.length() - 1);
      }
      if (s.endsWith(".")) {
        s = s.substring(0, s.length() - 1);
      }
    }
    return s;
  }

  public Object get(String key)
    throws JSONException
  {
    Object o = opt(key);
    if (o == null) {
      throw new JSONException("JSONObject[" + quote(key) +
        "] not found.");
    }
    return o;
  }

  public boolean getBoolean(String key)
    throws JSONException
  {
    Object o = get(key);
    if ((o.equals(Boolean.FALSE)) || (
      ((o instanceof String)) &&
      (((String)o).equalsIgnoreCase("false")))) {
      return false;
    }
    if ((o.equals(Boolean.TRUE)) || (
      ((o instanceof String)) &&
      (((String)o).equalsIgnoreCase("true")))) {
      return true;
    }
    throw new JSONException("JSONObject[" + quote(key) +
      "] is not a Boolean.");
  }

  public double getDouble(String key)
    throws JSONException
  {
    Object o = get(key);
    try
    {
      return (o instanceof Number) ?
        ((Number)o).doubleValue() :
        Double.valueOf((String)o).doubleValue();
    }
    catch (Exception e)
    {
      throw new JSONException("JSONObject[" + quote(key) +
        "] is not a number.");
    }
  }

  public int getInt(String key)
    throws JSONException
  {
    Object o = get(key);
    return (o instanceof Number) ?
      ((Number)o).intValue() : (int)getDouble(key);
  }

  public JSONArray getJSONArray(String key)
    throws JSONException
  {
    Object o = get(key);
    if ((o instanceof JSONArray)) {
      return (JSONArray)o;
    }
    throw new JSONException("JSONObject[" + quote(key) +
      "] is not a JSONArray.");
  }

  public JSONObject getJSONObject(String key)
    throws JSONException
  {
    Object o = get(key);
    if ((o instanceof JSONObject)) {
      return (JSONObject)o;
    }
    throw new JSONException("JSONObject[" + quote(key) +
      "] is not a JSONObject.");
  }

  public long getLong(String key)
    throws JSONException
  {
    Object o = get(key);
    return (o instanceof Number) ?
      ((Number)o).longValue() : Long.valueOf((String)o).longValue();
  }

  public static String[] getNames(JSONObject jo)
  {
    int length = jo.length();
    if (length == 0) {
      return null;
    }
    Iterator i = jo.keys();
    String[] names = new String[length];
    int j = 0;
    while (i.hasNext())
    {
      names[j] = ((String)i.next());
      j++;
    }
    return names;
  }

  public static String[] getNames(Object object)
  {
    if (object == null) {
      return null;
    }
    Class klass = object.getClass();
    Field[] fields = klass.getFields();
    int length = fields.length;
    if (length == 0) {
      return null;
    }
    String[] names = new String[length];
    for (int i = 0; i < length; i++) {
      names[i] = fields[i].getName();
    }
    return names;
  }

  public String getString(String key)
    throws JSONException
  {
    return get(key).toString();
  }

  public boolean has(String key)
  {
    return this.map.containsKey(key);
  }

  public boolean isNull(String key)
  {
    return NULL.equals(opt(key));
  }

  public Iterator keys()
  {
    return this.map.keySet().iterator();
  }

  public int length()
  {
    return this.map.size();
  }

  public JSONArray names()
  {
    JSONArray ja = new JSONArray();
    Iterator keys = keys();
    while (keys.hasNext()) {
      ja.put(keys.next());
    }
    return ja.length() == 0 ? null : ja;
  }

  public static String numberToString(Number n)
    throws JSONException
  {
    if (n == null) {
      throw new JSONException("Null pointer");
    }
    testValidity(n);



    String s = n.toString();
    if ((s.indexOf('.') > 0) && (s.indexOf('e') < 0) && (s.indexOf('E') < 0))
    {
      while (s.endsWith("0")) {
        s = s.substring(0, s.length() - 1);
      }
      if (s.endsWith(".")) {
        s = s.substring(0, s.length() - 1);
      }
    }
    return s;
  }

  public Object opt(String key)
  {
    return key == null ? null : this.map.get(key);
  }

  public boolean optBoolean(String key)
  {
    return optBoolean(key, false);
  }

  public boolean optBoolean(String key, boolean defaultValue)
  {
    try
    {
      return getBoolean(key);
    }
    catch (Exception e) {}
    return defaultValue;
  }

  public JSONObject put(String key, Collection value)
    throws JSONException
  {
    put(key, new JSONArray(value));
    return this;
  }

  public double optDouble(String key)
  {
    return optDouble(key, (0.0D / 0.0D));
  }

  public double optDouble(String key, double defaultValue)
  {
    try
    {
      Object o = opt(key);
      return (o instanceof Number) ? ((Number)o).doubleValue() :
        new Double((String)o).doubleValue();
    }
    catch (Exception e) {}
    return defaultValue;
  }

  public int optInt(String key)
  {
    return optInt(key, 0);
  }

  public int optInt(String key, int defaultValue)
  {
    try
    {
      return getInt(key);
    }
    catch (Exception e) {}
    return defaultValue;
  }

  public JSONArray optJSONArray(String key)
  {
    Object o = opt(key);
    return (o instanceof JSONArray) ? (JSONArray)o : null;
  }

  public JSONObject optJSONObject(String key)
  {
    Object o = opt(key);
    return (o instanceof JSONObject) ? (JSONObject)o : null;
  }

  public long optLong(String key)
  {
    return optLong(key, 0L);
  }

  public long optLong(String key, long defaultValue)
  {
    try
    {
      return getLong(key);
    }
    catch (Exception e) {}
    return defaultValue;
  }

  public String optString(String key)
  {
    return optString(key, "");
  }

  public String optString(String key, String defaultValue)
  {
    Object o = opt(key);
    return o != null ? o.toString() : defaultValue;
  }

  public JSONObject put(String key, boolean value)
    throws JSONException
  {
    put(key, value ? Boolean.TRUE : Boolean.FALSE);
    return this;
  }

  public JSONObject put(String key, double value)
    throws JSONException
  {
    put(key, new Double(value));
    return this;
  }

  public JSONObject put(String key, int value)
    throws JSONException
  {
    put(key, new Integer(value));
    return this;
  }

  public JSONObject put(String key, long value)
    throws JSONException
  {
    put(key, new Long(value));
    return this;
  }

  public JSONObject put(String key, Map value)
    throws JSONException
  {
    put(key, new JSONObject(value));
    return this;
  }

  public JSONObject put(String key, Object value)
    throws JSONException
  {
    if (key == null) {
      throw new JSONException("Null key.");
    }
    if (value != null)
    {
      testValidity(value);
      this.map.put(key, value);
    }
    else
    {
      remove(key);
    }
    return this;
  }

  public JSONObject putOnce(String key, Object value)
    throws JSONException
  {
    if ((key != null) && (value != null))
    {
      if (opt(key) != null) {
        throw new JSONException("Duplicate key \"" + key + "\"");
      }
      put(key, value);
    }
    return this;
  }

  public JSONObject putOpt(String key, Object value)
    throws JSONException
  {
    if ((key != null) && (value != null)) {
      put(key, value);
    }
    return this;
  }

  public static String quote(String string)
  {
    if ((string == null) || (string.length() == 0)) {
      return "\"\"";
    }
    char c = '\000';

    int len = string.length();
    StringBuffer sb = new StringBuffer(len + 4);


    sb.append('"');
    for (int i = 0; i < len; i++)
    {
      char b = c;
      c = string.charAt(i);
      switch (c)
      {
      case '"':
      case '\\':
        sb.append('\\');
        sb.append(c);
        break;
      case '/':
        if (b == '<') {
          sb.append('\\');
        }
        sb.append(c);
        break;
      case '\b':
        sb.append("\\b");
        break;
      case '\t':
        sb.append("\\t");
        break;
      case '\n':
        sb.append("\\n");
        break;
      case '\f':
        sb.append("\\f");
        break;
      case '\r':
        sb.append("\\r");
        break;
      default:
        if ((c < ' ') || ((c >= '') && (c < ' ')) || (
          (c >= ' ') && (c < '℀')))
        {
          String t = "000" + Integer.toHexString(c);
          sb.append("\\u" + t.substring(t.length() - 4));
        }
        else
        {
          sb.append(c);
        }
        break;
      }
    }
    sb.append('"');
    return sb.toString();
  }

  public Object remove(String key)
  {
    return this.map.remove(key);
  }

  public Iterator sortedKeys()
  {
    return new TreeSet(this.map.keySet()).iterator();
  }

  public static Object stringToValue(String s)
  {
    if (s.equals("")) {
      return s;
    }
    if (s.equalsIgnoreCase("true")) {
      return Boolean.TRUE;
    }
    if (s.equalsIgnoreCase("false")) {
      return Boolean.FALSE;
    }
    if (s.equalsIgnoreCase("null")) {
      return NULL;
    }
    char b = s.charAt(0);
    if (((b >= '0') && (b <= '9')) || (b == '.') || (b == '-') || (b == '+'))
    {
      if (b == '0') {
        if ((s.length() > 2) && (
          (s.charAt(1) == 'x') || (s.charAt(1) == 'X'))) {
          try
          {
            return new Integer(
              Integer.parseInt(s.substring(2), 16));
          }
          catch (Exception localException) {}
        } else {
          try
          {
            return new Integer(Integer.parseInt(s, 8));
          }
          catch (Exception localException1) {}
        }
      }
      try
      {
        if ((s.indexOf('.') > -1) || (s.indexOf('e') > -1) || (s.indexOf('E') > -1)) {
          return Double.valueOf(s);
        }
        Long myLong = new Long(s);
        if (myLong.longValue() == myLong.intValue()) {
          return new Integer(myLong.intValue());
        }
        return myLong;
      }
      catch (Exception localException2) {}
    }
    return s;
  }

  static void testValidity(Object o)
    throws JSONException
  {
    if (o != null) {
      if ((o instanceof Double))
      {
        if ((((Double)o).isInfinite()) || (((Double)o).isNaN())) {
          throw new JSONException(
            "JSON does not allow non-finite numbers.");
        }
      }
      else if (((o instanceof Float)) && (
        (((Float)o).isInfinite()) || (((Float)o).isNaN()))) {
        throw new JSONException(
          "JSON does not allow non-finite numbers.");
      }
    }
  }

  public JSONArray toJSONArray(JSONArray names)
    throws JSONException
  {
    if ((names == null) || (names.length() == 0)) {
      return null;
    }
    JSONArray ja = new JSONArray();
    for (int i = 0; i < names.length(); i++) {
      ja.put(opt(names.getString(i)));
    }
    return ja;
  }

  public String toString()
  {
    try
    {
      Iterator keys = keys();
      StringBuffer sb = new StringBuffer("{");
      while (keys.hasNext())
      {
        if (sb.length() > 1) {
          sb.append(',');
        }
        Object o = keys.next();
        sb.append(quote(o.toString()));
        sb.append(':');
        sb.append(valueToString(this.map.get(o)));
      }
      sb.append('}');
      return sb.toString();
    }
    catch (Exception e) {}
    return null;
  }

  public String toString(int indentFactor)
    throws JSONException
  {
    return toString(indentFactor, 0);
  }

  String toString(int indentFactor, int indent)
    throws JSONException
  {
    int n = length();
    if (n == 0) {
      return "{}";
    }
    Iterator keys = sortedKeys();
    StringBuffer sb = new StringBuffer("{");
    int newindent = indent + indentFactor;
    if (n == 1)
    {
      Object o = keys.next();
      sb.append(quote(o.toString()));
      sb.append(": ");
      sb.append(
        valueToString(this.map.get(o), indentFactor, indent));
    }
    else
    {
      while (keys.hasNext())
      {
        Object o = keys.next();
        if (sb.length() > 1) {
          sb.append(",\n");
        } else {
          sb.append('\n');
        }
        for (int j = 0; j < newindent; j++) {
          sb.append(' ');
        }
        sb.append(quote(o.toString()));
        sb.append(": ");
        sb.append(
          valueToString(this.map.get(o), indentFactor, newindent));
      }
      if (sb.length() > 1)
      {
        sb.append('\n');
        for (int j = 0; j < indent; j++) {
          sb.append(' ');
        }
      }
    }
    sb.append('}');
    return sb.toString();
  }

  static String valueToString(Object value)
    throws JSONException
  {
    if ((value == null) || (value.equals(null))) {
      return "null";
    }
    if ((value instanceof JSONString))
    {
      Object o;
      try
      {
        o = ((JSONString)value).toJSONString();
      }
      catch (Exception e)
      {
        throw new JSONException(e);
      }
       if ((o instanceof String)) {
        return (String)o;
      }
      throw new JSONException("Bad value from toJSONString: " + o);
    }
    if ((value instanceof Number)) {
      return numberToString((Number)value);
    }
    if (((value instanceof Boolean)) || ((value instanceof JSONObject)) ||
      ((value instanceof JSONArray))) {
      return value.toString();
    }
    if ((value instanceof Map)) {
      return new JSONObject((Map)value).toString();
    }
    if ((value instanceof Collection)) {
      return new JSONArray((Collection)value).toString();
    }
    if (value.getClass().isArray()) {
      return new JSONArray(value).toString();
    }
    return quote(value.toString());
  }

  static String valueToString(Object value, int indentFactor, int indent)
    throws JSONException
  {
    if ((value == null) || (value.equals(null))) {
      return "null";
    }
    try
    {
      if ((value instanceof JSONString))
      {
        Object o = ((JSONString)value).toJSONString();
        if ((o instanceof String)) {
          return (String)o;
        }
      }
    }
    catch (Exception localException)
    {
      if ((value instanceof Number)) {
        return numberToString((Number)value);
      }
      if ((value instanceof Boolean)) {
        return value.toString();
      }
      if ((value instanceof JSONObject)) {
        return ((JSONObject)value).toString(indentFactor, indent);
      }
      if ((value instanceof JSONArray)) {
        return ((JSONArray)value).toString(indentFactor, indent);
      }
      if ((value instanceof Map)) {
        return new JSONObject((Map)value).toString(indentFactor, indent);
      }
      if ((value instanceof Collection)) {
        return new JSONArray((Collection)value).toString(indentFactor, indent);
      }
      if (value.getClass().isArray()) {
        return new JSONArray(value).toString(indentFactor, indent);
      }
    }
    return quote(value.toString());
  }

  public Writer write(Writer writer)
    throws JSONException
  {
    try
    {
      boolean b = false;
      Iterator keys = keys();
      writer.write(123);
      while (keys.hasNext())
      {
        if (b) {
          writer.write(44);
        }
        Object k = keys.next();
        writer.write(quote(k.toString()));
        writer.write(58);
        Object v = this.map.get(k);
        if ((v instanceof JSONObject)) {
          ((JSONObject)v).write(writer);
        } else if ((v instanceof JSONArray)) {
          ((JSONArray)v).write(writer);
        } else {
          writer.write(valueToString(v));
        }
        b = true;
      }
      writer.write(125);
      return writer;
    }
    catch (IOException e)
    {
      throw new JSONException(e);
    }
  }
}
