package com.lance.demo.spring.batch.operator;

import com.lance.demo.spring.batch.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
public class ExplorerDemoTest extends TestConfig{
    @Autowired
    private ExplorerDemo explorerDemo;
    @Test
    public void exploreGetRunning() throws Exception {
        Set<JobExecution> runningJob = explorerDemo.getRunningJob();
        Assert.assertNotNull(runningJob);
    }

}