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
package org.fs.common;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractManager {

    protected abstract String  getClassTag();
    protected abstract boolean isLogEnabled();


    protected void log(final String msg) {
        log(Log.DEBUG, msg);
    }

    protected void log(final int lv, final String msg) {
      if (isLogEnabled()) {
        Log.println(lv, getClassTag(), msg);
      }
    }

    protected void log(Throwable exp) {
      StringWriter str = new StringWriter(128);
      PrintWriter  ptr = new PrintWriter(str);
      exp.printStackTrace(ptr);
      log(Log.ERROR, str.toString());
    }
}
