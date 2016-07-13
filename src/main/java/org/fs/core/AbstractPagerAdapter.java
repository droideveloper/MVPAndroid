package org.fs.core;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractPagerAdapter
 */
public abstract class AbstractPagerAdapter<D> extends FragmentPagerAdapter {

    protected List<D> dataSet = null;

    public AbstractPagerAdapter(FragmentManager fragmentManager, List<D> dataSet) {
        super(fragmentManager);
        this.dataSet = dataSet;
    }

    protected abstract String getClassTag();

    protected abstract boolean isLogEnabled();

    protected abstract Fragment onBind(int position, D element);

    protected final void log(final String str) {
        log(Log.DEBUG, str);
    }

    @Override
    public final Fragment getItem(int position) {
        return onBind(position, getItemAtIndex(position));
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

    public void appendData(@NonNull D data, boolean front) {
        if(dataSet != null) {
            if (front) {
                dataSet.add(0, data);
            } else {
                dataSet.add(data);
            }
        }
    }

    public void appendData(@NonNull List<D> data, boolean front) {
        if(dataSet != null) {
            if(front) {
                dataSet.addAll(0, data);
            } else {
                dataSet.addAll(data);
            }
        }
    }

    @Override
    public final int getCount() {
        return dataSet == null
                ? 0
                : dataSet.size();
    }

    protected final D getItemAtIndex(int index) {
        int limit = dataSet.size();
        if(index < 0 || index >= limit || limit == 0)
            return null;
        return dataSet.get(index);
    }
}
