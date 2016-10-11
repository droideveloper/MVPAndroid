/*
 * Copyright (C) 2016 Fatih.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fs.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.fs.exception.AndroidException;

public final class StringUtility {

  public final static String EMPTY = "";
  public final static String WHITE_SPACE = " ";
  private final static char[] hexBuffers = "0123456789ABCDEF".toCharArray();


  private StringUtility() {
    throw new AndroidException("no instance for you!");
  }

  /**
   * Object is null if object is String instance then checks extra for empty String control..
   * can pass any type.
   * @param object
   * @param <T>
   * @return
   */
  public static <T> boolean isNullOrEmpty(T object) {
    if(object == null)
      return true;
    if(object instanceof String) {
      String str = (String)object;
      return isEmpty(str);
    }
    return false;
  }

  /**
   * get from android.util.TextUtils.isEmpty(CharSequence str)
   * copy it from there because since it is in android package asked for
   * androidTest so it's here with another implementation of that method
   * what they do is same nothing extra or special
   * @param str
   * @return
   */
  public static boolean isEmpty(CharSequence str) {
    if (str == null || str.length() == 0)
      return true;
    else
      return false;
  }

  /**
   * provides if this String instance contains start and end tags of Html
   * @param str
   * @return
   */
  public static boolean hasHtmlTag(final String str) {
    //we assume that if it contains html start and end tag
      //we hit the Html tag some ignore occur like <br />, we don't care about it for now!.
    return !isNullOrEmpty(str)
        && str.contains("<")
        && str.contains("/>");
  }

  /**
   *
   * @param sink bytes to convert hex String
   * @return String representation of byte array
   */
  public static String toHexString(byte[] sink) {
    PreconditionUtility.checkNotNull(sink, "sink is null.");
    char[] hexChars = new char[sink.length * 2];
    for (int i = 0, z = sink.length; i < z; i++) {
      int v = sink[i] & 0xFF;
      hexChars[i * 2] = hexBuffers[v >>> 4];
      hexChars[i * 2 + 1] = hexBuffers[v & 0x0F];
    }
    return new String(hexChars);
  }

  /**
   *
   * @param str String to convert hex of sha1
   * @return sha1 of hex as string
   */
  public static String toSha1Hex(String str) {
    PreconditionUtility.checkNotNull(str, "str is null or empty.");
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      md.update(str.getBytes(Charset.forName("utf-8")), 0, str.length());
      byte[] buffer = md.digest();
      return toHexString(buffer);
    } catch (NoSuchAlgorithmException errorAlgorithm) {
      throw new AndroidException(errorAlgorithm);
    }
  }

  public static String toMd5Hex(String str) {
    PreconditionUtility.checkNotNull(str, "str is null or empty");
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(str.getBytes(Charset.forName("utf-8")), 0, str.length());
      byte[] buffer = md5.digest();
      return toHexString(buffer);
    } catch (NoSuchAlgorithmException notFoundError) {
      throw new AndroidException(notFoundError);
    }
  }
}
