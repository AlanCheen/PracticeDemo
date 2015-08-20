package com.example;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alanchen on 15/8/20.
 */
public class ThreadPoolPratice {

    static BlockingDeque<Runnable> working = new LinkedBlockingDeque<>(1);
    static ThreadFactory factory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {

            return new Thread(r, "factory:" + System.currentTimeMillis());
        }
    };

    //    RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
//        @Override
//        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//            executor.
//        }
//    };
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, working, factory);


    public static void main(String[] args) {


        for (int i = 0; i < 50; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("name:"+Thread.currentThread().getName());
                }
            });
        }

    }

}
