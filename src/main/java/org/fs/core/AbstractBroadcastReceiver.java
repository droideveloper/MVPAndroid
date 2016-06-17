package org.fs.core;

import android.content.BroadcastReceiver;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 02/11/15.
 * as org.fs.core.AbstractBroadcastReciever
 */
public abstract class AbstractBroadcastReceiver extends BroadcastReceiver {

    public AbstractBroadcastReceiver() {
        super();
    }

    protected abstract String getClassTag();

    protected abstract boolean isLogEnabled();

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
}
