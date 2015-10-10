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


    static Observable<List<String>> query() {
        List<String> s = Arrays.asList("Java", "Android", "Ruby", "Ios", "Swift");
        return Observable.just(s);
    }

    static Observable<String> addPre(String lan) {
        return Observable.just("addPre_" + lan);
    }


    public static void main(String[] args) {

//        mapOperator();
//        fromOperator();
//        flatMapOperator();
//        otherOperator();

    }

    private static void otherOperator() {
        query().flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return addPre(s);
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.contains("a");
            }
        }).take(3)
          .doOnNext(new Action1<String>() {
              @Override
              public void call(String s) {
                  System.out.println("doOnNext:" + s);
              }
          }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private static void flatMapOperator() {
        query().flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                // 将List进行From处理
                return Observable.from(strings);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("_flatMap:" + s);
            }
        });


        query().flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return addPre(s);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private static void fromOperator() {
        //它接收一个集合作为输入，然后每次输出一个元素给subscriber：
        final List<String> s = Arrays.asList("Java", "Android", "Ruby", "Ios", "Swift");
        Observable.from(s).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
        Observable.from(new String[]{"Java", "Android"}).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("_from:" + s);
            }
        });
    }

    private static void mapOperator() {
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
    }
}
