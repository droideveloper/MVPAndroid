package org.fs.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import org.fs.util.AppCompatProgressBarUtility;

/**
 * Created by Fatih on 10/06/16.
 * as org.fs.widget.AppCompatProgressBar
 */
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
