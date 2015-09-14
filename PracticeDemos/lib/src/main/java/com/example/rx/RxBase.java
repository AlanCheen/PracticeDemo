package com.example.rx;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by alanchen on 15/9/11.
 */
public class RxBase {


    static Observable<String> mStringObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello Rxjava");
            //Notice 如果onCompleted 先调用了,那么后面的onNext onError则会被无视掉,但是不是等同于return,后面其他的代码还是会执行
            subscriber.onCompleted();
//            System.out.println("看看onCompleted调用了还会不会有输出");
            subscriber.onError(new Throwable("hehehe"));
        }
    });

    static Subscriber<String> mStringSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            System.out.println("onCompleted");

        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError:" + e.getMessage());
        }

        @Override
        public void onNext(String s) {
            System.out.println(s);
        }
    };

    /**
     * Observable.just就是用来创建只发出一个事件就结束的Observable对象
     * 它在call里自动调用了 onNext 和 onCompleted
     */
    static Observable<String> simpleObservable = Observable.just("Hell,simple observable");


    /**
     * 如果不关心OnComplete和OnError，我们只需要在onNext的时候做一些处理，这时候就可以使用Action1类。
     */
    static Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println("Action1<String> onNextAction:" + s);
        }
    };

    static Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            System.out.println("Action1<Throwable> onErrorAction:");
        }
    };


    static Action0 onCompleteAction = new Action0() {
        @Override
        public void call() {
            System.out.println("Action0 onCompleteAction:");
        }
    };

    public static void main(String[] args) {

//        mStringObservable.subscribe(mStringSubscriber);//执行后 输出 Hello Rxjava   onCompleted
//
//        simpleObservable.subscribe(mStringSubscriber);//执行后 输出 Hell,simple observable  onCompleted
//
//        //Notice  如果调用onError 这个单个的onNextAction 会报错
        mStringObservable.subscribe(onNextAction);
        mStringObservable.subscribe(onNextAction, onErrorAction);
        mStringObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
//
//
        Observable.just("Hello simple onaction1").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }
}
