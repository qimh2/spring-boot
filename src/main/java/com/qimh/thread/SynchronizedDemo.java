package com.qimh.thread;

public class SynchronizedDemo {


    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        new Thread(()->{
            try {
                synchronizedDemo.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                synchronizedDemo2.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public synchronized void test() throws InterruptedException {
        System.out.println("====start");
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("====end");

    }
}
