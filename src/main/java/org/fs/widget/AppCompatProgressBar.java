/*
 * Core Android Copyright (C) 2016 Fatih.
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
package org.fs.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import org.fs.util.AppCompatProgressBarUtility;

public class AppCompatProgressBar extends ProgressBar {

    private AppCompatProgressBarUtility mAppCompatProgressBarUtility;
    private AppCompatDrawableManager    mDrawableManager;

    public AppCompatProgressBar(Context context) {
      super(context);
    }

    public AppCompatProgressBar(Context context, AttributeSet attrs) {
      this(context, attrs, android.support.v7.appcompat.R.attr.progressBarStyle);
    }

    public AppCompatProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);

      mDrawableManager = AppCompatDrawableManager.get();
      mAppCompatProgressBarUtility = new AppCompatProgressBarUtility(this, mDrawableManager);
      mAppCompatProgressBarUtility.loadFromAttributes(attrs, defStyleAttr);
    }
}
