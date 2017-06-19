package com.lance.demo.spring.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
public class JobDemoListener implements JobExecutionListener{

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("before job=====================");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("after job=====================");

    }


    @BeforeJob // TODO: 2017/6/19  暂不可用
    public void before(JobExecution jobExecution) {
        System.out.println("=====================");
    }

    @AfterJob // TODO: 2017/6/19
    public void after(JobExecution jobExecution) {
        System.out.println("=====================");

    }
}
