package org.fs.util;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

import org.fs.exception.AndroidException;

/**
 * Created by Fatih on 10/06/16.
 * as org.fs.util.ViewUtility
 */
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
