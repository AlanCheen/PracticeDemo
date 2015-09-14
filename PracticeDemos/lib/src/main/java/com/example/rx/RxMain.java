package com.example.rx;

import rx.functions.Action1;

/**
 * Created by alanchen on 15/9/14.
 */
public class RxMain {

    public static void main(String[]args){

        ConvertListener  convertListener = new ConvertListener();
        convertListener.observableListenerWrapper().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                  System.out.println(s);
            }
        });
        convertListener.setValue("第一次调用");
        convertListener.setValue("第二次调用");
    }

}
