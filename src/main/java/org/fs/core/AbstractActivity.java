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

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.fs.common.IPresenter;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractActivity<P extends IPresenter> extends AppCompatActivity {

    protected final P presenter;

    public AbstractActivity() {
      presenter = providePresenter();
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();
    protected abstract P        providePresenter();//implement this to provide presenter

    protected void log(final String str) {
      log(Log.DEBUG, str);
    }

    protected void log(Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter  prtWriter = new PrintWriter(strWriter);
      e.printStackTrace(prtWriter);
      log(Log.ERROR, strWriter.toString());
    }

    protected void log(final int lv, final String str) {
      if(isLogEnabled()) {
        Log.println(lv, getClassTag(), str);
      }
    }
}
