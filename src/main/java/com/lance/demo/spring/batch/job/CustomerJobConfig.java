package com.lance.demo.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by perdonare on 2017/6/19.
 */
@Configuration
public class CustomerJobConfig {
    @Bean
    public Job customerJob() {
        return new CustomerJob();
    }
}
