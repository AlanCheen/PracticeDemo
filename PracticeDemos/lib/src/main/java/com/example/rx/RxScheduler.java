package com.example.rx;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by alanchen on 15/9/20.
 */
public class RxScheduler {

    public static void main(String[] args) {

//        Observable.just("Hello Schedulers")
////                .subscribeOn(Schedulers.computation())
//                .subscribeOn(Schedulers.io())
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        System.out.println(Thread.currentThread().getName());
//                        return s;
//                    }
//                })
////                .subscribeOn(Schedulers.io())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println(Thread.currentThread().getName());
//                        System.out.println(s);
//                    }
//                });

        Observable.just("Hello Schedulers")
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        System.out.println("map:"+Thread.currentThread().getName());
                        return s;
                    }
                })
//                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("subscribe:"+Thread.currentThread().getName());
                    }
                });

    }
}
