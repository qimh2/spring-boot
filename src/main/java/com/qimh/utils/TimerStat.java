package com.qimh.utils;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

public class TimerStat {

    /**
     * 当前线程绑定ThreadLocal
     */
    private static final ThreadLocal<Map<String,Long>> TIMER_STAT_THREAD_LOCAL = new ThreadLocal();

    /**
     * 耗时统计-开始
     *
     * @return
     */
    public static void setStartTime(String methodName) {
        // 系统当前时间
        long time = System.currentTimeMillis();
        // 从threadLocal取值
        Map<String,Long> times = TIMER_STAT_THREAD_LOCAL.get();
        // 第一次统计耗时
        if (Objects.isNull(times)) {
            Map methodNameAndTime = Maps.newHashMap();
            methodNameAndTime.put(methodName, time);
            TIMER_STAT_THREAD_LOCAL.set(methodNameAndTime);
        }
    }

    /**
     * 耗时统计-结束
     *
     * @return
     */
    public static void totalTimeConsuming() {
        // 从threadLocal取值
        Map<String,Long> methodNameAndTime = TIMER_STAT_THREAD_LOCAL.get();
        if (Objects.nonNull(methodNameAndTime)) {
            // 支持嵌套，从最后一个开始取值
            long end = System.currentTimeMillis();
            for (Map.Entry<String, Long> entry : methodNameAndTime.entrySet()) {
                String method = entry.getKey();
                Long startTime = entry.getValue();
                System.out.println("线程：" + Thread.currentThread().getName() + "  方法 :" +method+"  执行耗时：毫秒" + String.valueOf(end-startTime));
            }
            //删除此线程局部变量的当前线程值
            TIMER_STAT_THREAD_LOCAL.remove();
            //System.out.println(TIMER_STAT_THREAD_LOCAL.get());
         }
    }

    public static void main(String[] args) {
        // 代码执行耗时统计
        // 记时开始-start
        TimerStat.setStartTime("main()");

        // 模拟代码实际执行时间（休眠800毫秒）
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        totalTimeConsuming();
    }
}
