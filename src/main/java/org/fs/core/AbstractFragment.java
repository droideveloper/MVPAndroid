package org.fs.core;

import android.support.v4.app.Fragment;
import android.util.Log;

import org.fs.common.IPresenter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractFragment
 */
public abstract class AbstractFragment<P extends IPresenter> extends Fragment {

    protected final P presenter;

    public AbstractFragment() {
        presenter = presenter();
    }

    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();
    protected abstract P        presenter();

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
     * calling this fragment system checks if this fragment attached to Window and its activity is alive...
     * @return true or false
     */
    protected boolean isCallingSafe() {
        return getActivity() != null && isAdded();
    }
}
