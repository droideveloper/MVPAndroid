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
package org.fs.util;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

import org.fs.exception.AndroidException;

public final class ViewUtility {

  private ViewUtility() {
    throw new AndroidException("no instance for ya!");
  }

  /**
   * Cast a View instance into directed parent kinda casting lazy
   * @param view View to be casted for that type
   * @param <T> T is the final type we are looking for
   * @return T type of View
   */
  @SuppressWarnings("unchecked") public static <T> T castAsField(View view) {
    return (T) view;
  }

  /**
   * Deeper call of view to find view inside of its parent
   * @param view View we search in
   * @param viewID id of view we will be searching
   * @param <T> filed of the view we assing it in type of T
   * @return T type of view TextView etc..
   */
  @SuppressWarnings("unchecked") public static <T> T findViewById(View view, @IdRes int viewID) {
    return (T) view.findViewById(viewID);
  }

  /**
   * Extract view for field casting lazy and ugly way but if you know the type of view than this is better for
   * less boiler plate code
   * @param activity activity instance
   * @param viewID view id
   * @param <T> type we will return
   * @return T type of view TextView etc..
   */
  public static <T> T findViewById(Activity activity, @IdRes int viewID) {
    return findViewById(activity.findViewById(android.R.id.content), viewID);
  }


}
