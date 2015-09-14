package com.example;

import com.example.http.HttpTest;

public class MyClass {

    public static void main(String[] args) {
        double a = 1;
        double b = Math.PI;
        double result = a / b;
//        System.out.println(":"+result);
//        System.out.println(calNum(123131));
//        System.out.println(calNum(234234));
//        System.out.println(calNum(4234));
//        System.out.println(calNum(555));
//        System.out.println("" + System.currentTimeMillis());

//        new Thread(()-> System.out.printf("")).start();
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpTest.hehe();
                }
            }).start();
        }

    }


    private static String calNum(int mTotalZan) {
        String content = "";
        if (mTotalZan > 9999) {
            int a = mTotalZan / 10000;//10 11
            int b = mTotalZan / 1000 - a * 10;
            content = a + "." + b + "W";
        } else if (mTotalZan > 999) {
            int a = mTotalZan / 1000;//10 11
            int b = mTotalZan / 100 - a * 10;
            content = a + "." + b + "K";
        }
        return content;
    }
}
