package com.lance.demo.spring.batch.operator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
@Service
public class RegistryDemo {
    @Autowired
    private JobRegistry jobRegistry;
    public Collection<String> getJobNames() {
        Collection<String> jobNames = jobRegistry.getJobNames();
        log.info("==============job names:{}",jobNames);
        return jobNames;
    }

}
