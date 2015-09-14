package com.example.rx;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by alanchen on 15/9/14.
 */
public class ConvertListener {

    interface ValueUpdateListener {
        void onValueChanged(String value);
    }

    //这里就不写list保存了
    ValueUpdateListener mValueUpdateListener;

    public void setValue(String value) {
        if (null != mValueUpdateListener) {
            mValueUpdateListener.onValueChanged(value);
        }
    }

    public void registerListener(ValueUpdateListener listener) {
        mValueUpdateListener = listener;
    }

    public void unregisterListener(ValueUpdateListener listener) {
        mValueUpdateListener = null;
    }

    public Observable<String> observableListenerWrapper() {

        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(final Subscriber<? super String> subscriber) {
                ValueUpdateListener listener = new ValueUpdateListener() {

                    @Override
                    public void onValueChanged(String value) {
                        if (subscriber.isUnsubscribed()) {
                            unregisterListener(this);
                        } else {
                            subscriber.onNext(value);
                        }
                    }
                };
                registerListener(listener);
            }
        });
    }
}
