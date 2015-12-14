package com.example.concurrent.exector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 程序亦非猿 on 15/10/20.
 */
public class ExectorMain {


    public static void main(String[] args) {

//        cache();
//        fixed();
//        single();
        threadPoolExecutor();
    }

    private static void cache() {

        //为
        ExecutorService cached = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            cached.execute(new MyThread());
        }
        cached.shutdown();
        //Notice shutdown 后调用 报错: Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@3fee733d rejected from java.util.concurrent.ThreadPoolExecutor@5acf9800[Shutting down, pool size = 5, active threads = 5, queued tasks = 0, completed tasks = 0]
//        cached.submit(new MyThread());
        /**
         * log:
         *
         Thread-0;11
         Thread-3;17
         Thread-2;15
         Thread-1;13
         Thread-4;19
         */
    }

    private static void fixed() {

        //指定并发数,超过的需要排队
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
                    executor.execute(new MyThread());
        }
        executor.shutdown();

        /**
         * log:
         Thread-1;13
         Thread-2;15
         Thread-0;11
         //--间隔了2秒再输出--
         Thread-3;17
         Thread-4;18
         */

    }

    private static void single() {

        //一次只能执行一个,如果有多个任务,得排队
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executor.execute(new MyThread());
        }
        executor.shutdown();

        /**
         * log:
         * 按顺序输出,并每次间隔2秒
         *
         Thread-0;11
         Thread-1;13
         Thread-2;14
         Thread-3;15
         Thread-4;16
         */

    }
    
    private static void threadPoolExecutor() {

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(128);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 0, TimeUnit.MILLISECONDS, queue);
        for (int i = 0; i < 5; i++) {
            executor.execute(new MyThread());
        }
    }


    private static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println(getName() + ";" + getId());
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
