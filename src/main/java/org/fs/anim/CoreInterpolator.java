package org.fs.anim;

import android.view.animation.Interpolator;

/**
 * Created by Fatih on 11/06/16.
 * as org.fs.anim.CoreInterpolator
 */
public class CoreInterpolator implements Interpolator {

    //(t - 1)^5 + 1 is the value of time position

    @Override
    public float getInterpolation(float t) {
        t -= 1.0f;
        return t * t * t * t * t  + 1.0f;
    }
}
