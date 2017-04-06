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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.List;

public abstract class AbstractRecyclerAdapter<D, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

  protected final List<D> dataSet;
  private final WeakReference<Context> contextRef;


  public AbstractRecyclerAdapter(@NonNull List<D> dataSet, Context context) {
    this.dataSet = dataSet;
    this.contextRef = context != null ? new WeakReference<>(context) : null;
  }

  protected abstract String getClassTag();
  protected abstract boolean isLogEnabled();

  @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    if(contextRef != null) {
      contextRef.clear();
    }
  }

  @Override public V onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(V viewHolder, int position) { }

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

  public void appendData(D data, boolean front) {
    if (front) {
      dataSet.add(0, data);
      notifyItemInserted(0);
    } else {
      dataSet.add(data);
      notifyItemInserted(getItemCount() - 1);
    }
  }

  public void removeData(D data) {
    int index = dataSet.indexOf(data);
    if (index != -1) {
      dataSet.remove(data);
      notifyItemRemoved(index);
    } else {
      dataSet.add(data);
    }
  }

  public void appendData(List<D> data, boolean front) {
    if (front) {
      dataSet.addAll(0, data);
      notifyItemRangeInserted(0, data.size());
    } else {
      dataSet.addAll(data);
      notifyItemRangeInserted(dataSet.size() - data.size(), data.size());
    }
  }

  @Override public int getItemCount() {
    return dataSet.size();
  }

  protected final D getItemAtIndex(int index) {
    int limit = dataSet.size();
    if(index < 0 || index >= limit || limit == 0)
      return null;
    return dataSet.get(index);
  }

  protected final Context getContext() {
    return contextRef != null ? contextRef.get() : null;
  }

  protected final LayoutInflater inflaterFactory() {
    Context context = getContext();
    if(context != null) {
      return LayoutInflater.from(context);
    }
    return null;
  }
}
