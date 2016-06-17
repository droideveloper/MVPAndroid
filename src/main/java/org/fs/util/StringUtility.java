package org.fs.util;

import org.fs.exception.AndroidException;

/**
 * Created by Fatih on 01/11/15.
 * as org.fs.util.Strings
 */
public final class StringUtility {

    private StringUtility() {
        throw new AndroidException("no instance for you!");
    }

    /**
     * Object is null if object is String instance then checks extra for empty String control..
     * can pass any type.
     * @param object
     * @param <T>
     * @return
     */
    public static <T> boolean isNullOrEmpty(T object) {
        if(object == null)
            return true;
        if(object instanceof String) {
            String str = (String)object;
            return isEmpty(str);
        }
        return false;
    }

    /**
     * get from android.util.TextUtils.isEmpty(CharSequence str)
     * copy it from there because since it is in android package asked for
     * androidTest so it's here with another implementation of that method
     * what they do is same nothing extra or special
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    /**
     * provides if this String instance contains start and end tags of Html
     * @param str
     * @return
     */
    public static boolean hasHtmlTag(final String str) {
        //we assume that if it contains html start and end tag
        //we hit the Html tag some ignore occur like <br />, we don't care about it for now!.
        return !isNullOrEmpty(str)
                && str.contains("<")
                && str.contains("/>");
    }
}
