package org.fs.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.AbstractEntity
 */
public abstract class AbstractEntity implements Parcelable {

    public AbstractEntity() { /*default constructor provided if gson will be used for serializing or de-serializing*/  }

    /**
     * forces developers to implement a constructor that takes Parcel instance as input
     * because this parcel reads serialized data into class variables or attributes.
     * @param input Parcel instance to read data from.
     */
    public AbstractEntity(Parcel input) { readParcel(input); }

    /**
     *
     * @return String for Logging purposes
     */
    protected abstract String getClassTag();

    /**
     *
     * @return boolean fro Logging purposes
     */
    protected abstract boolean isLogEnabled();


    /**
     *
     * @param input
     */
    protected abstract void readParcel(Parcel input);

    /**
     *
     * @param str
     */
    protected void log(final String str) {
        log(Log.DEBUG, str);
    }

    protected void log(Exception e) {
        StringWriter strWriter = new StringWriter();
        PrintWriter prtWriter = new PrintWriter(strWriter);
        e.printStackTrace(prtWriter);
        log(Log.ERROR, strWriter.toString());
    }

    /**
     *
     * @param lv
     * @param str
     */
    protected void log(final int lv, final String str) {
        if(isLogEnabled()) {
            Log.println(lv, getClassTag(), str);
        }
    }
}
