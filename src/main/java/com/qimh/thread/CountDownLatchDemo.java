package com.qimh.thread;

import com.qimh.dto.request.OrderInfo;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void updateStatus(OrderInfo orderInfo){
        countDownLatch.countDown();
        System.out.println("更新订单状态接口触发");
    }

    public static void orderCancel(OrderInfo orderInfo){
        countDownLatch.countDown();
        System.out.println("取消订单接口触发");
    }

    public static void syncStatusToNDMS(OrderInfo orderInfo){
        // 3y线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // 这里调用的是await()不是wait()
                    countDownLatch.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("调用ndms接口。。。。");
            }
        }).start();
    }

    public static void main(String[] args) {


        Runnable runnable = ()->{

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId("000001");
            orderInfo.setStatus("1");
            updateStatus(orderInfo);
            syncStatusToNDMS(orderInfo);
        };
        new Thread(runnable).start();

        Runnable runnable2 = ()->{
            OrderInfo orderInfo2 = new OrderInfo();
            orderInfo2.setOrderId("000001");
            orderInfo2.setCancelReason("取其他4s店维修了");
            orderCancel(orderInfo2);
        };
        new Thread(runnable2).start();

    }


}
