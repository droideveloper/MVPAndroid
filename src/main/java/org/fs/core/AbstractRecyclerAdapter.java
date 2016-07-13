package org.fs.core;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractRecyclerAdapter
 */
public abstract class AbstractRecyclerAdapter<D, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    protected final List<D>                 dataSet;
    protected final WeakReference<Context>  contextRef;

    public AbstractRecyclerAdapter(List<D> dataSet, Context context) {
        this.dataSet = dataSet;
        this.contextRef = context != null
                ? new WeakReference<>(context)
                : null;
    }

    protected abstract String   getClassTag();
    protected abstract boolean isLogEnabled();

    /**
     * this method used because we store context in weakReference we do not want any kind of leak just to be safe
     * @param recyclerView recyclerView that was using this adapter to fill itself with views (aho! ps. japanese)
     */
    @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if(contextRef != null) {
            contextRef.clear();
        }
    }

    @Override public V onCreateViewHolder(ViewGroup parent, int viewType) {
        //ignored, overriden in order to provide name as 'parent'
        return null;
    }

    @Override public void onBindViewHolder(V viewHolder, int position) {
        //ignored, overriden in order to provide name as 'viewHolder'
    }

    protected void log(final String str) {
        log(Log.DEBUG, str);
    }

    protected void log(Exception e) {
        StringWriter strWriter  = new StringWriter();
        PrintWriter prtWriter   = new PrintWriter(strWriter);
        e.printStackTrace(prtWriter);
        log(Log.ERROR, strWriter.toString());
    }

    protected void log(final int lv, final String str) {
        if(isLogEnabled()) {
            Log.println(lv, getClassTag(), str);
        }
    }

    /**
     * @param data  object, to added into current list
     * @param front if true it adds in front else back
     */
    public void appendData(D data, boolean front) {
        if(dataSet != null) {
            if (front) {
                dataSet.add(0, data);
                notifyItemInserted(0);
            } else {
                dataSet.add(data);
                notifyItemInserted(getItemCount() - 1);
            }
        }
    }

    public void removeData(D data) {
        if (dataSet != null) {
            int index = dataSet.indexOf(data);
            if (index != -1) {
                dataSet.remove(data);
                notifyItemRemoved(index);
            }
        }
    }

    /**
     * @param data list of objects, to added into current list
     * @param front if true it adds in front else back
     */
    public void appendData(List<D> data, boolean front) {
        if(dataSet != null) {
            if(front) {
                dataSet.addAll(0, data);
                notifyItemRangeInserted(0, data.size());
            } else {
                dataSet.addAll(data);
                notifyItemRangeInserted(dataSet.size() - data.size(), data.size());
            }
        }
    }

    /**
     *
     * @return 0 or actual count of dataSet
     */
    @Override public int getItemCount() {
        return dataSet == null
                ? 0
                : dataSet.size();
    }

    /**
     *
     * @param index index we need to look for
     * @return D or null
     */
    protected D getItemAt(int index) {
        int limit = dataSet != null ? dataSet.size() : 0;
        if(index < 0 || index >= limit || limit == 0)
            return null;
        return dataSet.get(index);
    }

    /**
     *
     * @return Context instance or null
     */
    protected Context getContext() {
        return contextRef != null
                ? contextRef.get()
                : null;
    }

    /**
     * inflater instance from context, if theme used then it will provides one
     * @return LayoutInflater instance
     */
    protected final LayoutInflater inflaterFactory() {
        Context context = getContext();
        if(context != null) {
            Resources.Theme theme = context.getTheme();
            if(theme != null) {
                return LayoutInflater.from(new ContextThemeWrapper(context, theme));
            } else {
                return LayoutInflater.from(context);
            }
        }
        return null;
    }
}
