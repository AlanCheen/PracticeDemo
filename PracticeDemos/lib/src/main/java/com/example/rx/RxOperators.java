package com.example.rx;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by alanchen on 15/9/13.
 */
public class RxOperators {


    static Observable<List<String>>query(String text){
        List<String> s = Arrays.asList("Java", "Android", "Ruby", "Ios", "Swift");
        return Observable.just(s);
    }


    public static void main(String[]args){

        //操作符就是为了解决对Observable对象的变换的问题，操作符用于在Observable和最终的Subscriber之间修改Observable发出的事件。
        //RxJava提供了很多很有用的操作符。
        // map:
        Observable.just("Hellp Map Operator").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return 2015;
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        List<String> s = Arrays.asList("Java", "Android", "Ruby", "Ios", "Swift");
        Observable.from(s).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }
}
