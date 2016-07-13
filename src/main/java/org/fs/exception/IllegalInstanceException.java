package org.fs.exception;

/**
 * Created by Fatih on 20/11/15.
 * as org.fs.exception.IllegalInstanceException
 */
public class IllegalInstanceException extends Exception {
    /**
     * for reflection junkies
     */
    public IllegalInstanceException() {
        super();
    }

    public IllegalInstanceException(Throwable e) { super(e); }

    public IllegalInstanceException(String str) {
        super(str);
    }

}
