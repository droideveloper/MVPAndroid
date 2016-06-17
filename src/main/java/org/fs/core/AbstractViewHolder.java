package org.fs.core;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 12/06/16.
 * as org.fs.core.AbstractViewHolder
 */
public abstract class AbstractViewHolder<D> {

    protected       D    data;
    protected final View view;

    public AbstractViewHolder(@NonNull View view) {
        this.view = view;
        //old style list views require this, some how
        view.setTag(this);
    }

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

    public final View getView() {
        return view;
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();
    protected abstract void     onBindData(D data);
}
