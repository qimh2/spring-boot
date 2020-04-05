//package com.qimh.springdemo.threadpool;
//
//import java.util.*;
//import java.util.concurrent.AbstractExecutorService;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class TrackingExecutor extends AbstractExecutorService {
//    private final ExecutorService service;
//    private final Set<Runnable> tasksCancelledAtShutdown= Collections.synchronizedSet(new HashSet<Runnable>());
//
//    TrackingExecutor(ExecutorService service){
//        this.service=service;
//    }
//    public List<Runnable> getCancelledTasks(){
//        if(!service.isTerminated()){
//            throw new IllegalStateException("");
//        }
//        return new ArrayList<>(tasksCancelledAtShutdown);
//    }
//
//    @Override
//    public void execute(final Runnable command) {
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    command.run();
//                }finally {
//                    if (isShutdown()&&Thread.currentThread().isInterrupted()){
//                        System.out.println("被打断了"+command);
//                        tasksCancelledAtShutdown.add(command);
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    public void shutdown() {
//        // 将ExecutorService的其他方法委托给service
//        service.shutdown();
//    }
//
//    @Override
//    public List<Runnable> shutdownNow() {
//        // 将ExecutorService的其他方法委托给service
//        return service.shutdownNow();
//    }
//
//    @Override
//    public boolean isShutdown() {
//        // 将ExecutorService的其他方法委托给service
//        return service.isShutdown();
//    }
//
//    @Override
//    public boolean isTerminated() {
//        // 将ExecutorService的其他方法委托给service
//        return service.isTerminated();
//    }
//
//    @Override
//    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
//        // 将ExecutorService的其他方法委托给service
//        return service.awaitTermination(timeout,unit);
//    }
//}
