/*
 * MVP Android Copyright (C) 2016 Fatih.
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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.MenuItem;
import dagger.android.support.AndroidSupportInjection;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Inject;
import org.fs.common.PresenterType;

public abstract class AbstractPreferenceFragment<P extends PresenterType> extends PreferenceFragmentCompat {

  @Inject protected P presenter;

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    AndroidSupportInjection.inject(this);
    super.onActivityCreated(savedInstanceState);
  }

  @Override public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    presenter.storeState(outState);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override public void onPause() {
    presenter.onPause();
    super.onPause();
  }

  @Override public void onStop() {
    presenter.onStop();
    super.onStop();
  }

  @Override public void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  public void onBackPressed() {
    throw new IllegalArgumentException("fragment does not support back press action");
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    presenter.activityResult(requestCode, resultCode, data);
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    presenter.requestPermissionResult(requestCode, permissions, grantResults);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return presenter.onOptionsItemSelected(item);
  }

  protected abstract String getClassTag();
  protected abstract boolean isLogEnabled();

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

  protected boolean isCallingSafe() {
    return getActivity() != null && isAdded();
  }
}
