package com.lance.demo.spring.batch.operator;

import com.lance.demo.spring.batch.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by perdonare on 2017/6/19.
 */
@Slf4j
public class OperatorDemoTest extends TestConfig{
    @Autowired
    private OperatorDemo operatorDemo;
    @Test
    public void operateRestart() throws Exception {
        operatorDemo.operateRestart();
    }

    @Test
    public void operateStart() throws Exception {
        operatorDemo.operateStart();
    }

}