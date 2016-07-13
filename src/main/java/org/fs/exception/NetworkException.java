package org.fs.exception;

/**
 * Created by Fatih on 23/11/15.
 * as org.fs.exception.NetworkException
 */
public class NetworkException extends RuntimeException {

    public NetworkException(Throwable throwable) {
        super(throwable);
    }

    public NetworkException(String detailMessage) {
        super(detailMessage);
    }

    public NetworkException() {
        super();
    }
}
