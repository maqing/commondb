package org.springframework.security.acls;

import org.springframework.util.Assert;

public abstract class AclFormattingUtils
{
  public static String demergePatterns(String original, String removeBits)
  {
    Assert.notNull(original, "Original string required");
    Assert.notNull(removeBits, "Bits To Remove string required");
    Assert.isTrue(original.length() == removeBits.length(), 
      "Original and Bits To Remove strings must be identical length");
    
    char[] replacement = new char[original.length()];
    for (int i = 0; i < original.length(); i++) {
      if (removeBits.charAt(i) == '.') {
        replacement[i] = original.charAt(i);
      } else {
        replacement[i] = '.';
      }
    }
    return new String(replacement);
  }
  
  public static String mergePatterns(String original, String extraBits)
  {
    Assert.notNull(original, "Original string required");
    Assert.notNull(extraBits, "Extra Bits string required");
    Assert.isTrue(original.length() == extraBits.length(), 
      "Original and Extra Bits strings must be identical length");
    
    char[] replacement = new char[extraBits.length()];
    for (int i = 0; i < extraBits.length(); i++) {
      if (extraBits.charAt(i) == '.') {
        replacement[i] = original.charAt(i);
      } else {
        replacement[i] = extraBits.charAt(i);
      }
    }
    return new String(replacement);
  }
  
  private static String printBinary(int i, char on, char off)
  {
    String s = Integer.toString(i, 2);
    String pattern = "................................";
    String temp2 = pattern.substring(0, pattern.length() - s.length()) + s;
    
    return temp2.replace('0', off).replace('1', on);
  }
  
  public static String printBinary(int i)
  {
    return printBinary(i, '*', '.');
  }
  
  public static String printBinary(int mask, char code)
  {
    Assert.doesNotContain(Character.toString(code), Character.toString('~'), 
      "~ is a reserved character code");
    Assert.doesNotContain(Character.toString(code), Character.toString('.'), 
      ". is a reserved character code");
    
    return printBinary(mask, '~', '.').replace('~', code);
  }
}
