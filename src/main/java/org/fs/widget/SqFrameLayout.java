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
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.fs.core.R;

public class SqFrameLayout extends FrameLayout {

    /**
     * if you want to use this with viePager Wrapper on ScrollView and want it width scaled square
     * use this value true ;) weird actions go on
     */
    private final static boolean DEFAULT_MAXIMUM = false;

    private boolean useMaximum;

    public SqFrameLayout(Context context) {
      super(context);
    }

    public SqFrameLayout(Context context, AttributeSet attrs) {
      this(context, attrs, -1);
    }

    public SqFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      loadStyle(context, attrs);
    }

    void loadStyle(Context context, AttributeSet attrs) {
      TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SqFrameLayout);
      useMaximum = array.getBoolean(R.styleable.SqFrameLayout_useMaximum, DEFAULT_MAXIMUM);
      array.recycle();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int width = MeasureSpec.getSize(widthMeasureSpec);
      int height = MeasureSpec.getSize(heightMeasureSpec);
      int sq;
      if(useMaximum) {
        sq = Math.max(width, height);
      } else {
        sq = Math.min(width, height);
      }
      setMeasuredDimension(sq, sq);
      //must be measured ?
      for(int i = 0; i < getChildCount(); i++) {
        View v = getChildAt(i);
        v.measure(widthMeasureSpec, heightMeasureSpec);
      }
    }
}
