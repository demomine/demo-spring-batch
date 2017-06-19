package com.lance.demo.spring.batch.job;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/**
 * Created by perdonare on 2017/6/19.
 */
public class JobDemoValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        if (parameters.getString("name") == null) {
            throw new JobParametersInvalidException("name is null");
        }
    }
}
