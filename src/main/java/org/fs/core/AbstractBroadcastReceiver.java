package org.fs.core;

import android.content.BroadcastReceiver;
import android.util.Log;

import org.fs.common.IPresenter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 02/11/15.
 * as org.fs.core.AbstractBroadcastReciever
 */
public abstract class AbstractBroadcastReceiver<P extends IPresenter> extends BroadcastReceiver {

    protected final P presenter;

    public AbstractBroadcastReceiver() {
        super();
        presenter = providePresenter();
    }

    protected abstract String  getClassTag();
    protected abstract boolean isLogEnabled();
    protected abstract P       providePresenter();


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
