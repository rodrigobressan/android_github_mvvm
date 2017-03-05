package com.rodrigobresan.githubmvvm.rx;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by rodrigobresan on 3/1/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */

public class MyRxBus {

    private static MyRxBus instance;

    private PublishSubject<Object> subject = PublishSubject.create();

    public static MyRxBus getInstance() {
        if (instance == null) {
            instance = new MyRxBus();
        }

        return instance;
    }

    /**
     * Pass any event down to event listeners
     * @param object
     */
    public void setString(Object object) {
        subject.onNext(object);
    }

    /**
     * Subscribe to this Observable. On event, do something e.g. replace a fragment
     * @return
     */
    public Observable<Object> getEvents() {
        return subject;
    }
}
