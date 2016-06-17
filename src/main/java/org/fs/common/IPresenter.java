package org.fs.common;

/**
 * Created by Fatih on 30/10/15.
 * as org.fs.core.IPresenter
 */

/**
 * also type definition
 */
public interface IPresenter {

    /**
     * context callback onCreate
     */
    void onCreate();

    /**
     * context callback onStart
     */
    void onStart();

    /**
     * context callback onPause
     */
    void onStop();

    /**
     * context callback onDestroy
     */
    void onDestroy();
}
