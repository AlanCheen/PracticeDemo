package com.example.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alanchen on 15/8/24.
 */
public class HttpTest {


    public static void hehe() {
        String u = "http://www.jianshu.com/p/36e089be8f38";
        try {
            URL url = new URL(u);
            URLConnection conn = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
            httpURLConnection.connect();
            httpURLConnection.getInputStream();
            System.out.printf(httpURLConnection.getResponseCode()+"");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
