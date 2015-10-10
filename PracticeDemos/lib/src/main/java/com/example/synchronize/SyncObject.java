package com.example.synchronize;

/**
 * Created by alanchen on 15/9/23.
 *
 *
 * SyncObject:这个是A
 SyncObject:这个是C
 间隔两秒后打印
 SyncObject:这个是B
 */
public class SyncObject {


    private static final Object lock = new Object();

    static void prtb() throws InterruptedException {
        synchronized (lock) {
            System.out.println("SyncObject:这个是B");
            Thread.sleep(2000);
        }
    }

    static void prta() throws InterruptedException {
        synchronized (lock) {
            System.out.println("SyncObject:这个是A");
            Thread.sleep(2000);
        }
    }

    static void prtc() {
        System.out.println("SyncObject:这个是C");
    }

    /**
     * 结果:
     * 先显示
     * 这个是A
     * 过两秒后在显示这个是B.
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
