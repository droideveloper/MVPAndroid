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
package org.fs.core;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.fs.common.PresenterType;

public abstract class AbstractFragment<P extends PresenterType> extends Fragment {

  public void showError(String errorString) {
    final View view = view();
    if(view != null) {
      Snackbar.make(view, errorString, Snackbar.LENGTH_LONG)
          .show();
    }
  }

  public void showError(String errorString, String actionTextString, final View.OnClickListener callback) {
    final View view = view();
    if(view != null) {
      final Snackbar snackbar = Snackbar.make(view, errorString, Snackbar.LENGTH_LONG);
      snackbar.setAction(actionTextString, new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (callback != null) {
            callback.onClick(v);
          }
          snackbar.dismiss();
        }
      });
      snackbar.show();
    }
  }

  public String getStringResource(@StringRes int stringId) {
    return getString(stringId);
  }

  public Context getContext() {
    return getActivity();
  }

  public boolean isAvailable() {
    return getActivity() != null && isAdded();
  }

  @Nullable protected View view() {
    return getView();
  }

  public void finish() {
    throw new IllegalArgumentException("fragment instances does not support finish options");
  }

  protected abstract String   getClassTag();
  protected abstract boolean  isLogEnabled();

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
}
