package com.example.concurrent;

/**
 * Created by 程序亦非猿 on 15/10/24.
 */
public class WaitNotify {

    public int money = 0;

    public void buy() {
        synchronized (this) {
            try {
                System.out.println("try to buy Thread:"+Thread.currentThread().getName());

                /**
                 * NOTICE  if的话,会出现负数
                 * NOTICE  while的话每次都会判断 不会出现金钱为负数
                 * 因为if直接下去了,while再notify后还会进入条件判断
                 */
//                if (money <= 0) {
                while (money == 0) {
                    System.out.println("屌丝!请先赚钱!~");
                    wait();
                }
                money = money - 100;
                System.out.println("土豪!购买成功!~~还剩下:" + money+";;Thread:"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void earn() {
        synchronized (this) {
            money = 500;
            /**
             * 如果只notify 那么购买成功的次数机会不可能是5
             屌丝!请先赚钱!~
             屌丝!请先赚钱!~
             屌丝!请先赚钱!~
             屌丝!请先赚钱!~
             屌丝!请先赚钱!~
             土豪!购买成功!~~还剩下:400
             */
//            notify();

            /**
             * notifyAll 则能购买成功五次 一定!~
             * **/
            notifyAll();
        }

    }


    public static final Object lock = new Object()
            ;
    private boolean open = false;
    public int test() {
//        synchronized (lock) { //这样会报错
        synchronized (this) {
            while (!open) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        final WaitNotify waitNotify = new WaitNotify();

//        testWaitNotify(waitNotify);

        new Thread(new Runnable() {
            @Override
            public void run() {
               int a =  waitNotify.test();
            }
        }).start();

    }

    private static void testWaitNotify(final WaitNotify waitNotify) {
        for (int i = 0; i < 7; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    waitNotify.buy();
                }
            }).start();
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        waitNotify.earn();
    }
}
