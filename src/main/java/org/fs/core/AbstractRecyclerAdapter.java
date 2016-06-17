package org.fs.core;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractRecyclerAdapter
 */
public abstract class AbstractRecyclerAdapter<D, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    private List<D>                 dataSet     = null;
    private WeakReference<Context>  contextRef  = null;

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
            } else {
                dataSet.add(data);
            }
        }
    }

    /**
     * @param data list of objects, to added into current list
     * @param front if true it adds in front else back
     */
    public void appenData(List<D> data, boolean front) {
        if(dataSet != null) {
            if(front) {
                dataSet.addAll(0, data);
            } else {
                dataSet.addAll(data);
            }
        }
    }

    /**
     *
     * @return 0 or actual count of dataSet
     */
    @Override
    public int getItemCount() {
        return dataSet == null
                ? 0
                : dataSet.size();
    }

    /**
     *
     * @param index index we need to look for
     * @return D or null
     */
    protected final D getItemAtIndex(int index) {
        int limit = dataSet.size();
        if(index < 0 || index >= limit || limit == 0)
            return null;
        return dataSet.get(index);
    }

    /**
     *
     * @return Context instance or null
     */
    protected final Context getContext() {
        return contextRef != null
                ? contextRef.get()
                : null;
    }
}
