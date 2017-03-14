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

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.view.View;

public interface ViewType {

  void showError(String errorString);

  void showError(String errorString, String buttonViewText, View.OnClickListener callback);

  String getStringResource(@StringRes int stringId);

  void startActivity(Intent intent);

  void startActivityForResult(Intent intent, int requestCode);

  void requestPermissions(String[] permissions, int requestCode);

  void finish();

  boolean isAvailable();

  Context getContext();
}
