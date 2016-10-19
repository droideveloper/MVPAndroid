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

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

  public AbstractRecyclerViewHolder(View itemView) {
    super(itemView);
  }

  protected abstract String   getClassTag();
  protected abstract boolean  isLogEnabled();

  /**
   * Need this public to be able to access it from outside
   * @param data T type of data
   */
  public abstract void     onBindView(T data);

  protected void log(final String str) {
    log(Log.DEBUG, str);
  }

  protected void log(Throwable error) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter  printWriter  = new PrintWriter(stringWriter);
    error.printStackTrace(printWriter);
    log(Log.ERROR, stringWriter.toString());
  }

  protected void log(final int lv, final String str) {
    if(isLogEnabled()) {
      Log.println(lv, getClassTag(), str);
    }
  }

  /**
   * method returns contentView from structured view
   * @return View instance of as root
   */
  protected View getView() {
    return itemView != null ? itemView : null;
  }
}
