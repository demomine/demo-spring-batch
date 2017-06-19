package com.lance.demo.spring.batch.operator;

import com.lance.demo.spring.batch.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
public class RegistryDemoTest extends TestConfig{
    @Autowired
    private RegistryDemo registryDemo;

    @Test
    public void getJobs() {
        Collection<String> collection = registryDemo.getJobNames();
        Assert.assertNotNull(collection);
    }
}