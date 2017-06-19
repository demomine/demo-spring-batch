package com.lance.demo.spring.batch.job;

import com.google.common.collect.Lists;
import com.lance.demo.spring.batch.service.SimpleTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by perdonare on 2017/6/16.
 */
@Configuration
public class JobConfig {
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job job() throws Exception {
        SimpleJobBuilder builder = jobs.get("job1").start(step());
        return builder.build();
    }

    @Bean
    public Job jobRestart() throws Exception {
        SimpleJobBuilder builder = jobs.get("jobRestart").start(step());
        return builder.preventRestart().build();
    }

    @Bean
    public Job jobRestart2(JobRepository jobRepository) throws Exception {
        SimpleJob job = new SimpleJob("joRestart2");
        job.setRestartable(true);
        job.setSteps(Lists.newArrayList(step()));
        job.setJobRepository(jobRepository);//JobRepository必须设置
        return job;
    }

    @Bean
    public Job jobInterceptor(JobRepository jobRepository, JobExecutionListener jobExecutionListener) throws Exception {
        SimpleJob job = new SimpleJob("joInterceptor");
        job.setRestartable(true);
        job.setSteps(Lists.newArrayList(step()));
        job.setJobExecutionListeners(new JobExecutionListener[]{jobExecutionListener});
        job.setJobRepository(jobRepository);//JobRepository必须设置
        return job;
    }

    @Bean // TODO: 2017/6/19  暂时未实现
    public Job jobExtends() throws Exception {
        SimpleJobBuilder builder = jobs.get("jobExtends").start(step());
        return builder.build();
    }

    @Bean
    public Job jobParamValid(JobDemoValidator jobDemoValidator) throws Exception {
        return jobs.get("jobParamValid").validator(jobDemoValidator).start(step()).build();
    }


    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobDemoListener();
    }

    @Bean
    public JobDemoValidator jobDemoValidator() {
        return new JobDemoValidator();
    }

    @Bean
    protected Step step() throws Exception {
        return steps.get("step1").tasklet(new SimpleTasklet()).build();
    }

}
