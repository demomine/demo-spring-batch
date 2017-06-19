package com.lance.demo.spring.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
public class CustomerJob implements Job {
    @Override
    public String getName() {
        return CustomerJob.class.getName();
    }

    @Override
    public boolean isRestartable() {
        return true;
    }

    @Override
    public void execute(JobExecution execution) {
        log.info("================customer job execution:{}", execution);

    }

    @Override
    public JobParametersIncrementer getJobParametersIncrementer() {
        return new RunIdIncrementer();
    }

    @Override
    public JobParametersValidator getJobParametersValidator() {
        return new DefaultJobParametersValidator();
    }
}
