package com.example;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 程序亦非猿 on 15/10/30.
 * aaaaa
 */
public class Regex {

    public static void main(String[]args) {

        String text = "爱的f 123130.33CNY";//16

//        Pattern pattern = Pattern.compile("\\d[A-Za-z]");//一个数字一个字母

        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(text);
        matcher.regionEnd();//16
        while (matcher.find()) {
            System.out.println(""+matcher.end());
        }
//        System.out.println(""+matcher.end());
//        matcher.regionEnd();
    }
}
