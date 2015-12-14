package com.example;


/**
 * Created by 程序亦非猿 on 15/11/10.
 * aaaaa
 */
public class Formatnum {

    public static void main(String[]args){


        System.out.println(getString(120102102030L));
    }

    public static final String getString(long num) {

        String result = "";

        if (num >= 1000000000) {
            result = String.format("%.2f",num*1.00f/1000000000);
        };

        return result;
    }
}
