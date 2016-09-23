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

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import org.fs.common.IPresenter;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractDialogFragment<P extends IPresenter> extends DialogFragment {

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

  /**
   * calling this fragment system checks if this fragment attached to Window and its activity is alive...
   * @return true or false
   */
  protected boolean isCallingSafe() {
    return getActivity() != null && isAdded();
  }

  /**
   * allowing state loss all the time to support various devices.
   */
  @Override public final void dismiss() {
    super.dismiss();//change of state loss
  }

  /**
   * overriden for committing with state loss
   * @param transaction FragmentTransaction instance
   * @param tag tag of fragment
   * @return int state
   */
  @Override public final int show(FragmentTransaction transaction, String tag) {
    return transaction.add(this, tag)
                      .commit();//change of state loss
  }

  /**
   * overriden for committing with state loss
   * @param manager FragmentManager instance
   * @param tag tag of fragment
   */
  @Override public final void show(FragmentManager manager, String tag) {
    FragmentTransaction trans = manager.beginTransaction();
    show(trans, tag);
  }
}
