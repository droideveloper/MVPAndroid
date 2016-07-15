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

    @SuppressWarnings("unchecked") public static <T> T castAsField(View view) {
      return (T) view;
    }

    @SuppressWarnings("unchecked") public static <T> T findViewById(View view, @IdRes int viewID) {
      return (T) view.findViewById(viewID);
    }

    public static <T> T findViewById(Activity activity, @IdRes int viewID) {
      return findViewById(activity.findViewById(android.R.id.content), viewID);
    }


}
