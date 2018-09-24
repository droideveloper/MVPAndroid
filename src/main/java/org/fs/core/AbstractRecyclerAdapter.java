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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.fs.util.ObservableList;
import org.fs.util.PropertyChangedListener;

public abstract class AbstractRecyclerAdapter<D, V extends AbstractRecyclerViewHolder<D>> extends RecyclerView.Adapter<V> implements PropertyChangedListener {

  protected final ObservableList<D> dataSet;

  public AbstractRecyclerAdapter(@NonNull ObservableList<D> dataSet) {
    this.dataSet = dataSet;
  }

  protected abstract String getClassTag();
  protected abstract boolean isLogEnabled();

  @Override public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    dataSet.registerPropertyChangedListener(this);
  }

  @Override public void onDetachedFromRecyclerView(@NonNull  RecyclerView recyclerView) {
    dataSet.unregisterPropertyChangedListener(this);
    super.onDetachedFromRecyclerView(recyclerView);
  }

  @Override public void onBindViewHolder(@NonNull V viewHolder, int position) {
    viewHolder.bind(getItemAtIndex(position));
  }

  @Override public void onViewRecycled(@NonNull V viewHolder) {
    super.onViewRecycled(viewHolder);
    viewHolder.unbind();
  }

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

  @Override public int getItemCount() {
    return dataSet.size();
  }

  protected final D getItemAtIndex(int index) {
    return dataSet.get(index);
  }

  @Override public void notifyItemsRemoved(int index, int size) {
    if (size == 1) {
      notifyItemRemoved(index);
    } else {
      notifyItemRangeRemoved(index, size);
    }
  }

  @Override public void notifyItemsChanged(int index, int size) {
    if (size == 1) {
      notifyItemChanged(index);
    } else {
      notifyItemRangeChanged(index, size);
    }
  }

  @Override public void notifyItemsInserted(int index, int size) {
    if (size == 1) {
      notifyItemInserted(index);
    } else {
      notifyItemRangeInserted(index, size);
    }
  }
}
