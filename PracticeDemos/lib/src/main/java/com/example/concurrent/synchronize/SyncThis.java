package com.example.concurrent.synchronize;

/**
 * Created by alanchen on 15/9/23.
 *
 * 练习 synchronized (String.class){}这种写法
 */
public class SyncThis {

    void prtb() throws InterruptedException {
        synchronized (this){
            System.out.println("SyncThis:这个是B");
            Thread.sleep(2000);
        }
    }
    void prta() throws InterruptedException {
        synchronized (this) {
            System.out.println("SyncThis:这个是A");
            Thread.sleep(2000);
        }
    }

    void prtc() {
        System.out.println("SyncThis:这个是C");
    }

    /**
     * 结果:
     * SyncClass:这个是A
     SyncClass:这个是C
     过两秒后
     SyncClass:这个是B
     */
    public void test() {
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
