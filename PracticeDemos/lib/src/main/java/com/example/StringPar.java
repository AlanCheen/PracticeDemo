package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alanchen on 15/9/9.
 */
public class StringPar {


    public static void main(String[] args) {


//        String content = "aole734c433198fd6b30://sourceapp=TaoLe.DouDiZhu&sourceappname=碰碰车&sourcescheme=taoledoudizhu&pic=http://s.5dktv.com/public/image/ddz_logo.png";
        String content = "aole734c433198fd6b30://sourceapp=TaoLe.DouDiZhu&sourceappname=碰碰车&sourcescheme=taoledoudizhu&pic=http://s.5dktv.com/public/image/ddz_logo.png";

        String regex = "=[*]+^&";
//        String regex = "(?<=\\=(.+?)(?=\\&)";
//        String regex = "=[*]+&";
//        String regex = "app";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(content);
        boolean ma = matcher.matches();
        String[] results = p.split(content);
        for (String result : results) {
            System.out.println(result);
        }
    }

}
