package org.fs.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

/**
 * Created by Fatih on 31/05/16.
 * as org.fs.core.AbstractOrmliteHelper
 */
public abstract class AbstractOrmliteHelper extends OrmLiteSqliteOpenHelper {

    public AbstractOrmliteHelper(Context context, String dbName, int dbVersion, int dbConfig) {
        super(context, dbName, null, dbVersion, dbConfig);
    }

    @Override
    public final void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            createTables(connectionSource);
        } catch (SQLException e) {
            log(e);
        }
    }

    @Override
    public final void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            dropTables(connectionSource);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            log(e);
        }
    }

    protected abstract void     createTables(ConnectionSource cs) throws SQLException;
    protected abstract void     dropTables(ConnectionSource cs) throws SQLException;
    protected abstract String   getClassTag();
    protected abstract boolean  isLogEnabled();


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
