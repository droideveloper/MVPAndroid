package org.fs.common;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 26/11/15.
 * as org.fs.common.AbstractPresenter
 */
public abstract class AbstractPresenter<V extends IView> {

    protected final V view;

    public AbstractPresenter(@NonNull final V view) {
        this.view = view;
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();

    protected void log(String str) {
        log(Log.DEBUG, str);
    }

    protected void log(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter  printWriter  = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        log(Log.ERROR, stringWriter.toString());
    }

    protected void log(int lv, String str) {
        if(isLogEnabled()) {
            Log.println(lv, getClassTag(), str);
        }
    }
}
