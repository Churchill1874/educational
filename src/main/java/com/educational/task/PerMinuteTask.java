package com.educational.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 定时任务类
 */
@Slf4j
@Component
public class PerMinuteTask {


    /**
     * 每分钟执行定时任务
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void perMinute() {
        LocalDateTime currentTime = LocalDateTime.now();
        //定时检查 满足时间条件就去拉取新闻

    }

}