package org.fs.util;

import org.fs.exception.AndroidException;

/**
 * Created by Fatih on 23/11/15.
 * as org.fs.util.PreconditionUtility
 */
public final class PreconditionUtility {

    private PreconditionUtility() {
        throw new AndroidException("no instance for you!");
    }

    /**
     * ensures string empty or null
     * @param t
     * @param errorMessage
     * @param <T>
     */
    public static <T> void checkNotNull(T t, String errorMessage) {
        if(StringUtility.isNullOrEmpty(t)) {
            throw new AndroidException(errorMessage);
        }
    }
}
