package com.qimh.schedule;

import com.qimh.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qiminhui
 * 任务执行类
 */
@Component
public class Task implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);
    @Value("${clue.date.todayBeforeDays}")
    private Integer todayBeforeDays;
    @Override
    public void run() {
        LOGGER.info("前多少天:{}",DateUtils.getTodayBeforeDays(DateUtils.getCurrentTime(),todayBeforeDays));
        LOGGER.info("任务执行run.....");
    }
}
