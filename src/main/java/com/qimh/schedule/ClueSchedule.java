package com.qimh.schedule;

import com.qimh.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author qiminhui
 * 定时的获取数据库中数据，并修改另外一张表的记录
 */
@Component
@Order(value = 1)
public class ClueSchedule implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClueSchedule.class);


    @Value(("${timer.executeTime}"))
    private String executeTime;
    @Value(("${timer.oneDay}"))
    private Long oneDay;
    @Autowired
    private Task task;

    private static long start = new Date().getTime();
    private static final ScheduledExecutorService excutor = Executors.newSingleThreadScheduledExecutor();

    /**
     * 每晚20：00定时获取数据的方法
     */
    private void schedule(){


//        Thread thread = new Thread(() -> {
//
//            long end = new Date().getTime();
//            LOGGER.info("time wait:"+(end-start)/1000+" s,this is 线程");
//        },"线程");
//
//        /*command：执行线程
//         *initialDelay：初始化延时
//         *period：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
//         *unit：计时单位
//         */
//        excutor.scheduleWithFixedDelay(thread,0, 1 , TimeUnit.MINUTES);


        /**
         * command：执行线程
           initialDelay：初始化延时，若initialDelay为负数则马上执行
           period：两次开始执行最小间隔时间--周期执行间隔时间
           unit：计时单位
         */
//        Task task = new Task();
        long oneDay =  this.oneDay * 1000;//60 * 1000;//24 * 60 * 60 * 1000;
        long initDelay  = DateUtils.getTimeMillis(executeTime) - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        excutor.scheduleAtFixedRate(
                task,
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);


    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        schedule();
    }
}
