package com.example.synchronize;

/**
 * Created by alanchen on 15/9/23.
 *
 * 练习 synchronized (String.class){}这种写法
 */
public class SyncClass {

    static void prtb() throws InterruptedException {
        synchronized (String.class){
            System.out.println("SyncClass:这个是B");
            Thread.sleep(2000);
        }
    }
    static void prta() throws InterruptedException {
        synchronized (String.class) {
            System.out.println("SyncClass:这个是A");
            Thread.sleep(2000);
        }
    }

    static void prtc() {
        System.out.println("SyncClass:这个是C");
    }

    /**
     * 结果:
     * SyncClass:这个是A
     SyncClass:这个是C
     过两秒后
     SyncClass:这个是B
     */
    public static void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    prta();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    prtb();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                prtc();
            }
        }).start();
    }

}
