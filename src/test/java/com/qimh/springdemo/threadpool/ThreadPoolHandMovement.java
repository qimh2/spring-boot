package com.qimh.springdemo.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 虽然提交了10个任务，
 * 但是线程池只有5线程来执行任务，
 * 其他任务得等到其他线程执行完之后才会从队列里获取任务来执行
 */
public class ThreadPoolHandMovement {
    public static void main(String[] args) {
        ExecutorService executor =new ThreadPoolExecutor(5,5,1,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new NameableThreadFactory("queryThreadPool"));
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                System.out.println("threadName:" + Thread.currentThread().getName() + " thread id is: " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


        }

        executor.shutdown();




//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (true){
//                    System.out.println(i++);
//                }
//
//            }
//        });
//        List<Runnable> runnables = executor.shutdownNow();
//        System.out.println("runnables:" + runnables);
    }

    static class NameableThreadFactory implements ThreadFactory{
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NameableThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name+"-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
