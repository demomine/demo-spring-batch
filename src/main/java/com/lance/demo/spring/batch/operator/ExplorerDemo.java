package com.lance.demo.spring.batch.operator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
@Service
public class ExplorerDemo {
    @Autowired
    private JobExplorer jobExplorer;


    public Set<JobExecution> getRunningJob() {
        Set<JobExecution> jobSet = jobExplorer.findRunningJobExecutions("job1");
        log.info("=====================job execution set:{}",jobSet);
        return jobSet;
    }
}
