package org.fs.core;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.fs.common.IPresenter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractActivity
 */
public abstract class AbstractActivity<P extends IPresenter> extends AppCompatActivity {

    protected final P presenter;

    public AbstractActivity() {
        presenter = presenter();
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();
    protected abstract P        presenter();//implement this to provide presenter

    protected void log(final String str) {
        log(Log.DEBUG, str);
    }

    protected void log(Exception e) {
        StringWriter strWriter = new StringWriter();
        PrintWriter  prtWriter = new PrintWriter(strWriter);
        e.printStackTrace(prtWriter);
        log(Log.ERROR, strWriter.toString());
    }

    protected void log(final int lv, final String str) {
        if(isLogEnabled()) {
            Log.println(lv, getClassTag(), str);
        }
    }
}
