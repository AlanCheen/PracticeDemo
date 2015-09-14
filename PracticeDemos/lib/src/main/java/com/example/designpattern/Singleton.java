package com.example.designpattern;

/**
 * Created by alanchen on 15/9/10.
 */
public class Singleton {

    private static Singleton ins;
    public static synchronized Singleton getIns() {
        if (null == ins) {
            ins = new Singleton();
        }
        return ins;
    }
}
