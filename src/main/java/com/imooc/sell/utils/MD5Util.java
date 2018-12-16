
/**
 * Project Name:sell.<br/> 
 * File Name:MD5Util.java.<br/> 
 * Package Name:com.imooc.sell.utils.<br/> 
 * Date:2017年12月12日下午12:29:41.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.utils;

import java.security.MessageDigest;

/**
 * ClassName: MD5Util. <br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: ADD REASON(可选).<br/>
 * Date: 2017年12月12日 下午12:29:41. <br/>
 * Desc: ADD DESC(可选). <br/>
 * 
 * @author 王艳军
 * @version
 * @since JDK 1.6
 */

public class MD5Util {

  private static String byteArrayToHexString(byte b[]) {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      resultSb.append(byteToHexString(b[i]));
    }

    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0)
      n += 256;
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  public static String MD5Encode(String origin, String charsetname) {
    String resultString = null;
    try {
      resultString = new String(origin);
      MessageDigest md = MessageDigest.getInstance("MD5");
      if (charsetname == null || "".equals(charsetname))
        resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
      else
        resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
    } catch (Exception exception) {
    }
    return resultString;
  }

  private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

  public static String md5(String str) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(str.getBytes());
      byte b[] = md.digest();
      int i;
      StringBuffer buf = new StringBuffer("");
      for (int offset = 0; offset < b.length; offset++) {
        i = b[offset];
        if (i < 0) {
          i += 256;
        }
        if (i < 16) {
          buf.append("0");
        }
        buf.append(Integer.toHexString(i));
      }
      str = buf.toString();
    } catch (Exception e) {
      e.printStackTrace();

    }
    return str;
  }

  public static void main(String[] args) {
    System.out.println(md5("31119@qq.com" + "123456"));
    System.out.println(MD5Encode("dbf49f5eb8254b8bc4cbb044f915e59d", null));
    System.out.println(md5("mj1"));
    System.out.println(md5("123456!@#"));
    System.out.println(MD5Encode("b814b812ec4b322e19fae7bb78d4d330", null));
    System.out.println(md5("123456!@#").equals("b814b812ec4b322e19fae7bb78d4d330"));
  }

}
