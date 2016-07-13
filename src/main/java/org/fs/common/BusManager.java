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

    //changing this into static instance why ? because I don't want it to go through dagger2 with this just crazy
    private final static BusManager IMPL = new BusManager();

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


    /**
     * <p>Send event to registered objects or it's death</p>
     * @param event event object to send which should implement {@link org.fs.common.IEvent}
     */
    public static void send(Object event) {
        IMPL.post(event);
    }

    /**
     * <p>Register operation that object implements type {@link rx.functions.Action1}</p>
     * @param clazz implementation of {@link rx.functions.Action1}
     * @return instance of {@link rx.Subscription}
     */
    public static Subscription add(Action1<Object> clazz) {
        return IMPL.register(clazz);
    }

    /**
     * <p>Unregister operation that object implements type {@link rx.functions.Action1} </p>
     * @param clazz previous instance of registration {@link rx.Subscription}
     */
    public static void remove(Subscription clazz) {
        IMPL.unregister(clazz);
    }
}
