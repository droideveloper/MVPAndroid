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
package org.fs.core;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractViewHolder<D> {

    protected       D    data;
    protected final View view;

    public AbstractViewHolder(@NonNull View view) {
      this.view = view;
      //old style list views require this, some how
      view.setTag(this);
    }

    protected void log(String str) {
      log(Log.DEBUG, str);
    }

    protected void log(Exception e) {
      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter  = new PrintWriter(stringWriter);
      e.printStackTrace(printWriter);
      log(Log.ERROR, stringWriter.toString());
    }

    protected void log(int lv, String str) {
      if(isLogEnabled()) {
        Log.println(lv, getClassTag(), str);
      }
    }

    public final View getView() {
      return view;
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();
    protected abstract void     onBindView(D data);

}
