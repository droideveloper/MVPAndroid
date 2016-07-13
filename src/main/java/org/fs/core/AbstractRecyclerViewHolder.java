package org.fs.core;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractRecyvlerViewHolder
 */
public abstract class AbstractRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    public AbstractRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract String getClassTag();

    protected abstract boolean isLogEnabled();

    protected abstract void onBindView(T data);

    protected void log(final String str) {
        log(Log.DEBUG, str);
    }

    protected void log(Exception e) {
        StringWriter strWriter = new StringWriter();
        PrintWriter prtWriter = new PrintWriter(strWriter);
        e.printStackTrace(prtWriter);
        log(Log.ERROR, strWriter.toString());
    }

    protected void log(final int lv, final String str) {
        if(isLogEnabled()) {
            Log.println(lv, getClassTag(), str);
        }
    }

    /**
     * method returns contentView from structured view
     * @return
     */
    protected View getContentView() {
        return itemView != null ? itemView : null;
    }
}
