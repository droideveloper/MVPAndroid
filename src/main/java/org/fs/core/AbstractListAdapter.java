package org.fs.core;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fatih on 12/06/16.
 * as org.fs.core.AbstractArrayListAdapter
 */
public abstract class AbstractListAdapter<D, VH extends AbstractViewHolder<D>> extends BaseAdapter {

    protected final List<D>             dataSet;
    protected final LayoutInflater      inflater;

    private final Object                lock = new Object();

    public AbstractListAdapter(Context context) {
        this(context, new ArrayList<D>());
    }

    public AbstractListAdapter(Context context, @NonNull List<D> dataSet) {
        this.dataSet = dataSet;
        Resources.Theme theme = context.getTheme();
        if(theme != null) {
            this.inflater = LayoutInflater.from(new ContextThemeWrapper(context, theme));
        } else {
            this.inflater = LayoutInflater.from(context);
        }
    }

    public final void add(D object) {
        synchronized (lock) {
            if(dataSet != null) {
                if(!dataSet.contains(object)) {
                    dataSet.add(object);
                    notifyDataSetChanged();
                }
            }
        }
    }

    public final void addAll(@NonNull List<D> objects) {
        synchronized (lock) {
            if(dataSet != null) {
                if(!dataSet.containsAll(objects)) {
                    dataSet.addAll(objects);
                    notifyDataSetChanged();
                }
            }
        }
    }

    public final boolean remove(@NonNull D object) {
        synchronized (lock) {
            if(dataSet != null) {
                if(dataSet.contains(object)) {
                    dataSet.remove(object);
                    notifyDataSetChanged();
                    return true;
                }
            }
            return false;
        }
    }

    public final D removeAt(@IntRange(from = 0) int index) {
        synchronized (lock) {
            if(dataSet != null) {
                D removed;
                if(index < dataSet.size()) {
                    removed = dataSet.remove(index);
                    notifyDataSetChanged();
                    return removed;
                }
            }
            return null;
        }
    }

    public final D getItemAt(@IntRange(from = 0) int index) {
        synchronized (lock) {
            if(dataSet != null) {
                if(index < dataSet.size()) {
                    return dataSet.get(index);
                }
            }
            return null;
        }
    }

    @Override public int getCount() {
        return dataSet != null ? dataSet.size() : 0;
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

    @SuppressWarnings("unchecked") @Override public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            int viewType = getItemViewType(position);
            VH viewHolder = onCreateViewHolder(parent, viewType);
            onBindViewHolder(viewHolder, position);
            return viewHolder.getView();
        }
        VH viewHolder = (VH) convertView.getTag();
        onBindViewHolder(viewHolder, position);
        return viewHolder.getView();
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();
    protected abstract VH       onCreateViewHolder(ViewGroup parent, int viewType);
    protected abstract void     onBindViewHolder(VH viewHolder, int position);

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
}
