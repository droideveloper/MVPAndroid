package org.fs.core;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import org.fs.common.IPresenter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractDialogFragment
 */
public abstract class AbstractDialogFragment<P extends IPresenter> extends DialogFragment {

    protected final P presenter;

    public AbstractDialogFragment() {
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

    /**
     * allowing state loss all the time to support various devices.
     */
    @Override
    public final void dismiss() {
        dismissAllowingStateLoss();
    }

    /**
     * overriden for committing with state loss
     * @param transaction FragmentTransaction instance
     * @param tag tag of fragment
     * @return int state
     */
    @Override
    public final int show(FragmentTransaction transaction, String tag) {
        return transaction.add(this, tag)
                          .commitAllowingStateLoss();
    }

    /**
     * overriden for committing with state loss
     * @param manager FragmentManager instance
     * @param tag tag of fragment
     */
    @Override
    public final void show(FragmentManager manager, String tag) {
        FragmentTransaction trans = manager.beginTransaction();
        show(trans, tag);
    }
}
