package com.lance.demo.spring.batch.job;

import com.lance.demo.spring.batch.service.SimpleTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by perdonare on 2017/6/16.
 */
@Configuration
public class FileDownloadJob {
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
    protected Step step() throws Exception {
        return steps.get("step1").tasklet(new SimpleTasklet()).build();
    }

    @Bean
    protected Step ftpFileDownloadStep() {
        steps.get("ftpFileDownloadStep").tasklet()
    }

}
