//package com.qimh.springdemo.threadpool;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//public class Test {
//
//    private volatile TrackingExecutor executor;
//
//
//    public synchronized void start() {
//        executor = new TrackingExecutor(Executors.newFixedThreadPool(3));
//        for (int i = 0; i < 5; i++) {
//            final int finalI = i;
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    //......
//                    System.out.println(finalI);
//                }
//            });
//        }
//
//    }
//
//    public synchronized void stop() throws InterruptedException {
//        try {
//            System.out.println("已提交尚未开始= " + (executor.shutdownNow()));
//            if (executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
//                System.out.println("正在运行而被取消= " + executor.getCancelledTasks());
//            }
//        } finally {
//            executor = null;
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//
//        Test test = new Test();
//        test.start();
//        test.stop();
//    }
//}