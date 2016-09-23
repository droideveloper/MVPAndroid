/*
 * Copyright (C) 2016 Fatih.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fs.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

public abstract class AbstractOrmliteHelper extends OrmLiteSqliteOpenHelper {

  public AbstractOrmliteHelper(Context context, String dbName, int dbVersion, int dbConfig) {
    super(context, dbName, null, dbVersion, dbConfig);
  }


  @Override public final void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try {
      createTables(connectionSource);
    } catch (SQLException e) {
      log(e);
    }
  }

  @Override public final void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
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

  protected void log(Throwable error) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter  printWriter  = new PrintWriter(stringWriter);
    error.printStackTrace(printWriter);
    log(Log.ERROR, stringWriter.toString());
  }

  protected void log(final int lv, final String str) {
    if(isLogEnabled()) {
      Log.println(lv, getClassTag(), str);
    }
  }
}
