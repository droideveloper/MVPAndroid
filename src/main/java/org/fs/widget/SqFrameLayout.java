package org.fs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.fs.core.R;

/**
 * Created by Fatih on 10/06/16.
 * as org.fs.widget.SqFrameLayout
 */
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
