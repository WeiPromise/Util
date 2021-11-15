package com.promise.util;

/**
 * Created by leiwei on 2020/8/4 14:47
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        Thread[] t = new Thread[10];
        for (int i = 0; i < t.length; i++) {
            Integer finalI = i%2;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (finalI) {
                        System.out.println(Thread.currentThread().getName()+"====="+finalI);
                        try {
                            Thread.sleep(3000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
            t[i].start();
        }
        for (int i = 0; i <t.length ; i++) {
            t[i].join();
        }
    }

}
