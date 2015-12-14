package com.example;

/**
 * Created by 程序亦非猿 on 15/10/23.
 */
public class StringBufferP {

    public static void main(String[] args) {
        String a = "String";
        StringBuffer b = new StringBuffer("StringBuffer");

        methodA(a);
        methodB(b);

        System.out.println(a);
        System.out.println(b);
    }

    public static void methodA(String c){
        c = new String("newString");
        System.out.println(c);
    }
    public static void methodB(StringBuffer c){
        c = new StringBuffer("newStringBuffer");
        System.out.println(c);
    }

    /**
     输出:why?
     newString
     newStringBuffer
     String
     StringBuffer
     */
}
