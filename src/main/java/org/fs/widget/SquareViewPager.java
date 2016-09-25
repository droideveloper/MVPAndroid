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
package org.fs.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.fs.core.AbstractApplication;

public class SquareViewPager extends ViewPager {

  public SquareViewPager(Context context) {
    super(context);
  }

  public SquareViewPager(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);
    //simple way of creating square to min of width or height that user provides
    int minimum = Math.max(width, height);
    setMeasuredDimension(minimum, minimum);
  }

  protected void log(String msg) {
    log(Log.DEBUG, msg);
  }

  protected void log(Exception error) {
    StringWriter strWriter = new StringWriter(128);
    PrintWriter prtWriter = new PrintWriter(strWriter);
    error.printStackTrace(prtWriter);
    log(Log.ERROR, strWriter.toString());
  }

  protected void log(int lv, String msg) {
    if (isLogEnabled()) {
      Log.println(lv, getClassTag(), msg);
    }
  }

  protected String getClassTag() {
    return SquareViewPager.class.getSimpleName();
  }

  protected boolean isLogEnabled() {
    return AbstractApplication.isDebug();
  }

}