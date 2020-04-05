package com.qimh.springdemo.thread;



import com.google.common.collect.Maps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 演示：现在在sleep,wait,join 时 ，调用调用线程的interrupt()方法，通过异常来终止方法
 * 参考连接：https://blog.csdn.net/ywl470812087/article/details/88412255
 */
public class SleepInterruptUpgrade {

    static Map<Long, Thread> threads = Maps.newHashMap();

    public static void main(String[] args) {

        SleepThread2 t1 = new SleepThread2();

        t1.start();


        try {

//            Thread.sleep(5000);//主线程休眠
            System.out.println("main thread 休眠结束....");

        } catch (Exception e) {

            e.printStackTrace();

        }

        //t1.interrupt();//主动打断线程，使SleepThread线程抛出异常
        System.out.println(" thread.state :" + threads.get(t1.getId()).getState());
        threads.get(t1.getId()).interrupt();
        System.out.println("集合元素移除前个数："+ threads.size());
        //线程从map中移除
        threads.remove(t1.getId());
        System.out.println("集合元素移除后个数："+ threads.size());

    }

}

class SleepThread2 extends Thread {


    //    public synchronized void run() {
    public void run() {

        //线程放入map中
//        SleepInterruptUpgrade.threads.put(this.getId(), this);
        SleepInterruptUpgrade.threads.put(Thread.currentThread().getId(), Thread.currentThread());

        while (true) {

            try {

                SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");

                System.out.println(sim.format(new Date()));

//                sleep(1000);

//                wait();
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof  InterruptedException){
                    System.out.println("线程中断");
                    return;
                }


            }

        }

    }
}