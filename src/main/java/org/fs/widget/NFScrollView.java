package org.fs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

import org.fs.core.R;

/**
 * Created by Fatih on 10/06/16.
 * as org.fs.widget.NFScrollView
 */
public class NFScrollView extends ScrollView {

    private final static boolean DEFAULT_PROVIDE_FOCUS_ON_CHILDREN = true;
    private final static boolean DEFAULT_HAS_OVER_SCROLL           = false;
    private final static float   DEFAULT_OVER_SCROLL_PERCENTAGE    = .1f;
    private final static float   DEFAULT_OVER_SCROLL_DP            = 50f;

    private boolean provideFocusOnChildren;
    private boolean hasOverScroll;
    private boolean byPercentage;

    private float   overScrollBy;
    private int     overScrollDirection;

    public NFScrollView(Context context) {
        super(context);
    }

    public NFScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public NFScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadStyle(context, attrs);
    }

    /**
     * reads defined style parameters from xml
     * @param context Context instance
     * @param attrs AttributeSet instance
     */
    void loadStyle(Context context, AttributeSet attrs) {
        TypedArray array =  context.obtainStyledAttributes(attrs, R.styleable.NFScrollView);
        provideFocusOnChildren = array.getBoolean(R.styleable.NFScrollView_provideFocusOnChild, DEFAULT_PROVIDE_FOCUS_ON_CHILDREN);
        hasOverScroll = array.getBoolean(R.styleable.NFScrollView_hasOverScroll, DEFAULT_HAS_OVER_SCROLL);
        if(hasOverScroll) {
            if(array.hasValue(R.styleable.NFScrollView_overScrollByPercent)) {
                overScrollBy = array.getFloat(R.styleable.NFScrollView_overScrollByPercent, DEFAULT_OVER_SCROLL_PERCENTAGE);
                byPercentage = true;
            } else {
                if(array.hasValue(R.styleable.NFScrollView_overScrollBy)) {
                    overScrollBy = array.getDimension(R.styleable.NFScrollView_overScrollBy, DEFAULT_OVER_SCROLL_DP);
                    byPercentage = false;
                }
            }
            if(array.hasValue(R.styleable.NFScrollView_overScrollTo)) {
                overScrollDirection = array.getInt(R.styleable.NFScrollView_overScrollTo, 2);//default is 2, which is Y-axis
            }
        }
        array.recycle();
    }

    @Override protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return provideFocusOnChildren;
    }

    @Override protected boolean overScrollBy(int dx, int dy, int sx, int sy, int srx, int sry, int mx, int my, boolean touch) {
        if(hasOverScroll) {
            boolean directionX = overScrollDirection == 1;
            if (directionX) {
                //overScrollBy X-axis
                return super.overScrollBy(dx, dy, sx, sy, srx, sry, Math.round(overScrollBy), my, touch);
            } else {
                //overScrollBy Y-axis
                return super.overScrollBy(dx, dy, sx, sy, srx, sry, mx, Math.round(overScrollBy), touch);
            }
        }
        //no overScrollProvided
        return super.overScrollBy(dx, dy, sx, sy, srx, sry, mx, my, touch);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(hasOverScroll) {
            if(byPercentage) {
                boolean directionX = overScrollDirection == 1;
                if (directionX) {
                    int width = getMeasuredWidth();
                    overScrollBy *= width;
                } else {
                    int height = getMeasuredHeight();
                    overScrollBy *= height;
                }
            } else {
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                overScrollBy *= metrics.density;
            }
        }
    }
}
