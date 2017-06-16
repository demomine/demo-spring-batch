package com.lance.demo.spring.batch.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by perdonare on 2017/6/16.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public JobOperator jobOperator(final JobLauncher jobLauncher, final JobExplorer jobExplorer,
                                   final JobRepository jobRepository, final JobRegistry jobRegistry) throws Exception {
        return new SimpleJobOperator() {
            {
                setJobLauncher(jobLauncher);
                setJobExplorer(jobExplorer);
                setJobRepository(jobRepository);
                setJobRegistry(jobRegistry);
            }
        };
    }

    @Bean
    public JobExplorerFactoryBean jobExplorer(final DataSource dataSource) throws Exception {
        return new JobExplorerFactoryBean() {
            {
                setDataSource(dataSource);
            }
        };
    }

    @Bean
    public MapJobRegistry jobRegistry() throws Exception {
        return new MapJobRegistry();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegisterBeanPostProcess(final JobRegistry jobRegistry) throws Exception {
        return new JobRegistryBeanPostProcessor() {
            {
                setJobRegistry(jobRegistry);
            }
        };
    }
    @Bean
    public JobRepository jobRepository(DataSource dataSource, DataSourceTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepo = new JobRepositoryFactoryBean();
        jobRepo.setDataSource(dataSource);
        jobRepo.setTransactionManager(transactionManager);
        return jobRepo.getObject();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

}
