//package com.qimh.springdemo.threadpool;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * 虽然提交了10个任务，
// * 但是线程池只有5线程来执行任务，
// * 其他任务得等到其他线程执行完之后才会从队列里获取任务来执行
// */
//public class ThreadPoolTest {
//    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            executor.submit(() -> {
//                System.out.println("thread id is: " + Thread.currentThread().getId());
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//
//
//        }
//
//        executor.shutdown();
//
//
////        executor.submit(new Runnable() {
////            @Override
////            public void run() {
////                int i = 0;
////                while (true){
////                    System.out.println(i++);
////                }
////
////            }
////        });
////        List<Runnable> runnables = executor.shutdownNow();
////        System.out.println("runnables:" + runnables);
//    }
//}
