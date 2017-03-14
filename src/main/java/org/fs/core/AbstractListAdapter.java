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
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListAdapter<D, VH extends AbstractViewHolder<D>> extends BaseAdapter {

  protected final List<D> dataSet;
  protected final WeakReference<Context> contextRef;
  private final Object lock = new Object();

  public AbstractListAdapter(Context context) {
    this(context, new ArrayList<>());
  }

  public AbstractListAdapter(Context context, @NonNull List<D> dataSet) {
    this.dataSet = dataSet;
    this.contextRef = context != null ? new WeakReference<>(context) : null;
  }

  public final void add(D object) {
    synchronized (lock) {
      if(!dataSet.contains(object)) {
        dataSet.add(object);
        notifyDataSetChanged();
      }
    }
  }

  public final void addAll(@NonNull List<D> objects) {
    synchronized (lock) {
      if(!dataSet.containsAll(objects)) {
        dataSet.addAll(objects);
        notifyDataSetChanged();
      }
    }
  }

  public final boolean remove(@NonNull D object) {
    synchronized (lock) {
      if(dataSet.contains(object)) {
        dataSet.remove(object);
        notifyDataSetChanged();
        return true;
      }
      return false;
    }
  }

  public final D removeAt(@IntRange(from = 0) int index) {
    synchronized (lock) {
      D removed = null;
      if(index < dataSet.size()) {
        removed = dataSet.remove(index);
        notifyDataSetChanged();
      }
      return removed;
    }
  }

  public final D getItemAt(@IntRange(from = 0) int index) {
    synchronized (lock) {
      if(index < dataSet.size()) {
        return dataSet.get(index);
      }
      return null;
    }
  }

  @Override public int getCount() {
    return dataSet.size();
  }

  @Override public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override public long getItemId(int position) {
    return Long.MAX_VALUE;
  }

  @Override public int getViewTypeCount() {
    return super.getViewTypeCount();
  }

  @Override public Object getItem(int position) {
    return getItemAt(position);
  }

  @SuppressWarnings("unchecked")
  @Override public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null) {
      int viewType = getItemViewType(position);
      VH viewHolder = onCreateViewHolder(parent, viewType);
      onBindViewHolder(viewHolder, position);
      return viewHolder.getView();
    }
    Object tag = convertView.getTag();
    VH viewHolder = (VH) tag;
    onBindViewHolder(viewHolder, position);
    return viewHolder.getView();
  }

  protected abstract String   getClassTag();
  protected abstract boolean  isLogEnabled();
  protected abstract VH       onCreateViewHolder(ViewGroup parent, int viewType);
  protected abstract void     onBindViewHolder(VH viewHolder, int position);

  protected final Context getContext() {
    return contextRef != null ? contextRef.get() : null;
  }

  protected final LayoutInflater inflaterFactory() {
    final Context context = getContext();
    if(context != null) {
      return LayoutInflater.from(context);
    }
    return null;
  }

  protected void log(String str) {
    log(Log.DEBUG, str);
  }

  protected void log(Throwable error) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter  printWriter  = new PrintWriter(stringWriter);
    error.printStackTrace(printWriter);
    log(Log.ERROR, stringWriter.toString());
  }

  protected void log(int lv, String str) {
    if(isLogEnabled()) {
      Log.println(lv, getClassTag(), str);
    }
  }
}
