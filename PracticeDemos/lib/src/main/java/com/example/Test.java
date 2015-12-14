package com.example;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 程序亦非猿 on 15/11/24.
 * aaaaa
 */
public class Test {

    public static void main(String[]args) {

        String time = "2015-03-18T01:29:48.821Z";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(time);
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            System.out.println("year:" + year + "\r\n month: " + month + "\r\nday:" + day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
