/*
 * MVP Android Copyright (C) 2016 Fatih.
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

import java.security.MessageDigest;
import java.util.Locale;
import org.fs.exception.AndroidException;

public final class StringUtility {

  public final static String EMPTY = "";
  public final static String WHITE_SPACE = " ";
  private final static char[] hexBuffers = "0123456789ABCDEF".toCharArray();


  private StringUtility() {
    throw new AndroidException("no instance for you!");
  }

  public static <T> boolean isNullOrEmpty(T object) {
    if(object == null)
      return true;
    if(object instanceof String) {
      String str = (String)object;
      return isEmpty(str);
    }
    return false;
  }

  public static boolean isEmpty(CharSequence str) {
    if (str == null || str.length() == 0)
      return true;
    else
      return false;
  }


  public static boolean hasHtmlTag(final String str) {
    //we assume that if it contains html start and end tag
      //we hit the Html tag some ignore occur like <br />, we don't care about it for now!.
    return !isNullOrEmpty(str)
        && str.contains("<")
        && str.contains("/>");
  }


  public static String toHexString(byte[] sink) {
    PreconditionUtility.checkNotNull(sink, "sink is null.");
    StringBuilder strBuffer = new StringBuilder();
    for (int i = 0, z = sink.length; i < z; i++) {
      String hex = String.format(Locale.ENGLISH, "%02X", (sink[i] & 0xFF));
      strBuffer.append(hex);
    }
    String hashStr = strBuffer.toString();
    return hashStr.toUpperCase(Locale.ENGLISH);
  }

  public static String toSha1Hex(String str) {
    PreconditionUtility.checkNotNull(str, "str is null or empty.");
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] source = str.getBytes("UTF-8");
      md.update(source, 0, source.length);
      byte[] sink = md.digest();
      return toHexString(sink);
    } catch (Exception errorAlgorithm) {
      throw new AndroidException(errorAlgorithm);
    }
  }

  public static String toMd5Hex(String str) {
    PreconditionUtility.checkNotNull(str, "str is null or empty");
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      byte[] source = str.getBytes("UTF-8");
      md5.update(source, 0, source.length);
      byte[] sink = md5.digest();
      return toHexString(sink);
    } catch (Exception notFoundError) {
      throw new AndroidException(notFoundError);
    }
  }
}
