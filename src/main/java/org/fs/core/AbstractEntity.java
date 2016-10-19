/*
 * Core Android Copyright (C) 2016 Fatih.
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
package org.fs.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractEntity implements Parcelable {

  public AbstractEntity() { /*default constructor provided if gson will be used for serializing or de-serializing*/  }

  /**
   * forces developers to implement a constructor that takes Parcel instance as input
   * because this parcel reads serialized data into class variables or attributes.
   * @param input Parcel instance to read data from.
   */
  public AbstractEntity(Parcel input) {
    readParcel(input);
  }

  /**
   *
   * @return String for Logging purposes
   */
  protected abstract String getClassTag();

  /**
   *
   * @return boolean fro Logging purposes
   */
  protected abstract boolean isLogEnabled();


  /**
   *
   * @param input
   */
  protected abstract void readParcel(Parcel input);

  /**
   *
   * @param str
   */
  protected void log(final String str) {
    log(Log.DEBUG, str);
  }

  protected void log(Throwable error) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter  printWriter  = new PrintWriter(stringWriter);
    error.printStackTrace(printWriter);
    log(Log.ERROR, stringWriter.toString());
  }

  /**
   *
   * @param lv
   * @param str
   */
  protected void log(final int lv, final String str) {
    if(isLogEnabled()) {
      Log.println(lv, getClassTag(), str);
    }
  }
}
