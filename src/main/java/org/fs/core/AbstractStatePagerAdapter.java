package org.fs.core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractStatePagerAdapter
 */
public abstract class AbstractStatePagerAdapter<D> extends FragmentStatePagerAdapter {

    /**
     * we might want to access it in child class
     */
    protected List<D> dataSet = null;

    public AbstractStatePagerAdapter(FragmentManager fragmentManager, List<D> dataSet) {
        super(fragmentManager);
        this.dataSet = dataSet;
    }

    protected abstract String getClassTag();

    protected abstract boolean isLogEnabled();

    protected abstract Fragment onBind(int position, D element);

    protected final void log(final String str) {
        log(Log.DEBUG, str);
    }

    protected final void log(Exception e) {
        StringWriter strWriter = new StringWriter();
        PrintWriter prtWriter = new PrintWriter(strWriter);
        e.printStackTrace(prtWriter);
        log(Log.ERROR, strWriter.toString());
    }

    protected final void log(final int lv, final String str) {
        if(isLogEnabled()) {
            Log.println(lv, getClassTag(), str);
        }
    }

    @Override
    public final Fragment getItem(int position) {
        return onBind(position, getItemAtIndex(position));
    }

    @Override
    public final int getCount() {
        return dataSet == null
                ? 0
                : dataSet.size();
    }

    protected final D getItemAtIndex(int index) {
        int limit = dataSet.size();
        if (index < 0 || index >= limit || limit == 0)
            return null;
        return dataSet.get(index);
    }
}
