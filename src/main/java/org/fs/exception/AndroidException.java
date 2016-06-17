package org.fs.exception;

/**
 * Created by Fatih on 22/11/15.
 * as org.fs.exception.AndroidException
 */
public class AndroidException extends RuntimeException {

    public AndroidException() {
        super();
    }

    public AndroidException(Throwable throwable) {
        super(throwable);
    }

    public AndroidException(String detailMessage) {
        super(detailMessage);
    }
}
