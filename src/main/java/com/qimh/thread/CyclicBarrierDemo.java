package com.qimh.thread;


import com.qimh.dto.request.OrderInfo;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    static final CyclicBarrier CyclicBarrier = new CyclicBarrier(2);

    public static void updateStatus(OrderInfo orderInfo){
        try {
            System.out.println("更新订单状态接口触发" + Thread.currentThread().getName());
            CyclicBarrier.await();
            syncStatusToNDMS(orderInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public static void orderCancel(OrderInfo orderInfo){
        try {
            System.out.println("取消订单接口触发" + Thread.currentThread().getName());
            CyclicBarrier.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public static void syncStatusToNDMS(OrderInfo orderInfo){
        //此时前面两个线程变量数据已经落库，把数据从库里反差出来，即可实现两个接口的合并
        System.out.println("调用ndms接口。。。。" + orderInfo);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            Runnable runnable = ()->{

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId("000001" + String.valueOf(finalI));
                orderInfo.setStatus("1");
                updateStatus(orderInfo);
            };
            new Thread(runnable).start();

            Runnable runnable2 = ()->{
                OrderInfo orderInfo2 = new OrderInfo();
                orderInfo2.setOrderId("000001" + String.valueOf(finalI));
                orderInfo2.setCancelReason("取其他4s店维修了");
                orderCancel(orderInfo2);
            };
            new Thread(runnable2).start();
        }



    }


}
