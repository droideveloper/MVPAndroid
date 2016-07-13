package org.fs.common;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 11/07/16.
 * as org.fs.common.AbstractManager
 */
public abstract class AbstractManager {

    protected abstract String  getClassTag();
    protected abstract boolean isLogEnabled();


    protected void log(final String msg) {
        log(Log.DEBUG, msg);
    }

    protected void log(final int lv, final String msg) {
        if (isLogEnabled()) {
            Log.println(lv, getClassTag(), msg);
        }
    }

    protected void log(Throwable exp) {
        StringWriter str = new StringWriter(128);
        PrintWriter  ptr = new PrintWriter(str);
        exp.printStackTrace(ptr);
        log(Log.ERROR, str.toString());
    }
}
