package org.fs.common;

import org.fs.exception.AndroidException;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Fatih on 21/05/16.
 * as org.fs.common.BusManager
 */
public final class BusManager {

    /**
     * New implementation of event-bus, otto would be lovely but as you see we gotta do this in a way
     */
    private final Subject<Object, Object> rxBus = new SerializedSubject<>(PublishSubject.create());

    public void post(Object event) {
        if(event instanceof IEvent) {
            rxBus.onNext(event);
        } else {
            throw new AndroidException("your object must be IEvent type, implement it.");
        }
    }

    public Observable<Object> toObservable() {
        return rxBus;
    }

    public Subscription register(Action1<Object> clazz) {
        return rxBus.subscribe(clazz);
    }

    public void unregister(Subscription clazz) {
        //if it's null or not unsubscribed then we are good to unregister it
        if(clazz != null && !clazz.isUnsubscribed()) {
            clazz.unsubscribe();
        }
    }

    public boolean hasObservers() {
        return rxBus.hasObservers();
    }
}
