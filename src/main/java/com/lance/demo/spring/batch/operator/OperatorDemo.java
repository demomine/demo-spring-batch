package com.lance.demo.spring.batch.operator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
@Service
public class OperatorDemo {
    @Autowired
    private JobOperator jobOperator;
    public void operateRestart() {
        try {
            jobOperator.restart(1);
        } catch (JobInstanceAlreadyCompleteException e) {
            log.error("job already complete");
        } catch (NoSuchJobExecutionException e) {
            log.error("job already complete");
        } catch (NoSuchJobException e) {
            log.error("job not exists exception");
        } catch (JobRestartException e) {
            log.error("job restart exception");
        } catch (JobParametersInvalidException e) {
            log.error("job valid exception");
        }
    }

    public void operateStart() {
        try {
            jobOperator.start("job1","lance");
        } catch (NoSuchJobException e) {
            log.error("job not exists exception");
        } catch (JobParametersInvalidException e) {
            log.error("job valid exception");
        } catch (JobInstanceAlreadyExistsException e) {
            log.error("job already exists");
            e.printStackTrace();
        }
    }
}
