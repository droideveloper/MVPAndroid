package org.fs.core;

import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 25/11/15.
 * as org.fs.core.AbstractPreferenceFragment
 */
public abstract class AbstractPreferenceFragment extends PreferenceFragmentCompat {

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

    /**
     * calling this fragment system checks if this fragment attached to Window and its activity is alive...
     * @return true or false
     */
    protected boolean isCallingSafe() {
        return getActivity() != null && isAdded();
    }
}
