package com.lance.demo.spring.batch.job;

import com.lance.demo.spring.batch.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;

import java.util.HashMap;

/**
 * Created by perdonare on 2017/6/16.
 */
@Slf4j
public class JobTest extends TestConfig {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @Autowired
    private Job jobRestart;
    @Autowired
    private Job jobInterceptor;
    @Autowired
    private Job jobParamValid;
    @Autowired
    private Job customerJob;
    @Test
    public void job() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("random", new JobParameter(Long.valueOf((long)(Math.random() * 1000000.0D))));
        JobParameters jobParameters =  new JobParameters(parameters);
        jobLauncher.run(job, jobParameters);

    }

    @Test
    @Repeat(2)//如果设置restartable为false会报错JobRestartException
    public void jobRestart() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("1", new JobParameter("1"));//参数不一样的job不是同一个job不会引发RestartException
        JobParameters jobParameters =  new JobParameters(parameters);

        try {
            jobLauncher.run(jobRestart, jobParameters);
        } catch (JobRestartException e) {
            Assert.assertEquals(e.getMessage(),"JobInstance already exists and is not restartable");
        }
    }

    @Test
    public void jobInterceptor() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("random", new JobParameter(Long.valueOf((long)(Math.random() * 1000000.0D))));
        JobParameters jobParameters =  new JobParameters(parameters);
        jobLauncher.run(jobInterceptor, jobParameters);

    }

    @Test
    public void jobExtends() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("random", new JobParameter(Long.valueOf((long)(Math.random() * 1000000.0D))));
        JobParameters jobParameters =  new JobParameters(parameters);
        jobLauncher.run(jobInterceptor, jobParameters);

    }

    @Test
    public void jobValidator() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("name1", new JobParameter(Long.valueOf((long)(Math.random() * 1000000.0D))));
        JobParameters jobParameters =  new JobParameters(parameters);
        try {
            jobLauncher.run(jobParamValid, jobParameters);
        }  catch (JobParametersInvalidException e) {
            Assert.assertEquals(e.getMessage(),"name is null");
        }

    }

    @Test
    public void customerJob() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("random", new JobParameter(Long.valueOf((long)(Math.random() * 1000000.0D))));
        JobParameters jobParameters =  new JobParameters(parameters);
        jobLauncher.run(customerJob, jobParameters);
    }
}
