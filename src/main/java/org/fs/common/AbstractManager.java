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
package org.fs.common;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractManager {

  /**
   * Provide tag for concrete class instance
   *
   * @return String
   */
  protected abstract String  getClassTag();

  /**
   * Provide boolean for concrete class instance
   * should be used for BuildConfig.DEBUG
   *
   * @return Boolean
   */
  protected abstract boolean isLogEnabled();

  /**
   * Logging message
   *
   * @param msg String
   */
  protected void log(final String msg) {
      log(Log.DEBUG, msg);
  }

  /**
   * Logging message with Level
   *
   * @param lv Integer
   * @param msg String
   */
  protected void log(final int lv, final String msg) {
    if (isLogEnabled()) {
      Log.println(lv, getClassTag(), msg);
    }
  }

  /**
   * Logging error
   *
   * @param error Throwable
   */
  protected void log(Throwable error) {
    StringWriter str = new StringWriter(128);
    PrintWriter  ptr = new PrintWriter(str);
    error.printStackTrace(ptr);
    log(Log.ERROR, str.toString());
  }
}
