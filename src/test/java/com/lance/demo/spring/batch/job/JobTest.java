package com.lance.demo.spring.batch.job;

import com.lance.demo.spring.batch.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Test
    public void job() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("random", new JobParameter(Long.valueOf((long)(Math.random() * 1000000.0D))));
        JobParameters jobParameters =  new JobParameters(parameters);
        jobLauncher.run(job, jobParameters);

    }

    @Test
    public void jobRestart() throws Exception {
        HashMap parameters = new HashMap();
        parameters.put("1", new JobParameter("1"));
        JobParameters jobParameters =  new JobParameters(parameters);
        jobLauncher.run(jobRestart, jobParameters);

    }
}
