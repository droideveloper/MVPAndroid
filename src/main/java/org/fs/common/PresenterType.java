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
package org.fs.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public interface PresenterType {

  /**
   * View's resume lifecycle
   */
  void onResume();

  /**
   * View's pause lifecycle
   */
  void onPause();

  /**
   * View's start lifecycle
   */
  void onStart();

  /**
   * View's stop lifecycle
   */
  void onStop();

  /**
   * View's create lifecycle
   */
  void onCreate();

  /**
   * View's destroy lifecycle
   */
  void onDestroy();

  /**
   * View's backPressed lifecycle
   */
  void onBackPressed();

  /**
   * View's restoreState lifecycle
   *
   * @param restoreState previous state of view.
   */
  void restoreState(Bundle restoreState);

  /**
   * View's storeState lifecycle
   *
   * @param storeState current state of view.
   */
  void storeState(Bundle storeState);

  /**
   * View's activityResult lifecycle
   *
   * @param requestCode requestCode of call for result
   * @param resultCode responseCode of called for result
   * @param data data of called for result
   */
  void activityResult(int requestCode, int resultCode, Intent data);

  /**
   * View's requestPermissionResult lifecycle for API M and above
   *
   * @param requestCode requestCode of call for result
   * @param permissions permissions of called for result
   * @param grants grants of called for result
   */
  void requestPermissionResult(int requestCode, String[] permissions, int[] grants);

  /**
   * View's optionsItemSelected lifecycle callbacks
   *
   * @param item item that its actions destroyed
   * @return true if handled false otherwise
   */
  boolean onOptionsItemSelected(MenuItem item);
}
