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

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.fs.util.ObservableList;
import org.fs.util.PropertyChangedListener;

public abstract class AbstractStatePagerAdapter<D> extends FragmentStatePagerAdapter implements
    PropertyChangedListener {

  private final ObservableList<D> dataSet;

  public AbstractStatePagerAdapter(FragmentManager fragmentManager, @NonNull ObservableList<D> dataSet) {
    super(fragmentManager);
    this.dataSet = dataSet;
  }

  protected abstract String getClassTag();
  protected abstract boolean isLogEnabled();
  protected abstract Fragment bind(int position, D element);

  protected final void log(final String str) {
    log(Log.DEBUG, str);
  }

  protected void log(Throwable error) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter  printWriter  = new PrintWriter(stringWriter);
    error.printStackTrace(printWriter);
    log(Log.ERROR, stringWriter.toString());
  }

  protected final void log(final int lv, final String str) {
    if(isLogEnabled()) {
      Log.println(lv, getClassTag(), str);
    }
  }

  @Override public final Fragment getItem(int position) {
    return bind(position, getItemAtIndex(position));
  }

  @Override public final int getCount() {
    return dataSet.size();
  }

  protected final D getItemAtIndex(int index) {
    return dataSet.get(index);
  }

  public final void register() {
    dataSet.registerPropertyChangedListener(this);
  }

  public final void unregister() {
    dataSet.unregisterPropertyChangedListener(this);
  }

  @Override public void notifyItemsRemoved(int index, int size) {
    notifyDataSetChanged();
  }

  @Override public void notifyItemsChanged(int index, int size) {
    notifyDataSetChanged();
  }

  @Override public void notifyItemsInserted(int index, int size) {
    notifyDataSetChanged();
  }
}
