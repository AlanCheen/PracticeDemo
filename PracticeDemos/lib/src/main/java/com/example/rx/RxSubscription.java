package com.example.rx;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by alanchen on 15/9/24.
 */
public class RxSubscription {
    public static void main(String[] args) {

//        Subscription subscription = Observable.just("Hello subscription")
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println(s);
//                    }
//                });
//        System.out.println(subscription.isUnsubscribed());
//        subscription.unsubscribe();
//        System.out.println(subscription.isUnsubscribed());


        Subscription subscription = Observable.just("Hello subscription")
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(s);
                    }
                });

        //默认subscription 调用完
        System.out.println(subscription.isUnsubscribed());
        subscription.unsubscribe();
        System.out.println(subscription.isUnsubscribed());


    }
}
