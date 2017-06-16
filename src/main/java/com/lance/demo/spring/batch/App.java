package com.lance.demo.spring.batch;


import com.google.common.util.concurrent.AbstractIdleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by perdonare on 2017/5/9.
 */
@Slf4j
@ComponentScan("com.lance.demo.spring.batch")
public class App extends AbstractIdleService {
    private AnnotationConfigApplicationContext context;

    public static void main(String[] args) {
        App app = new App();
        app.startAsync();
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException ex) {
            log.error("ignore interruption");
        }
    }

    @Override
    protected void startUp() throws Exception {
        context = new AnnotationConfigApplicationContext(App.class);
        context.start();
        context.registerShutdownHook();
        log.info("service started successfully");

    }

    @Override
    protected void shutDown() throws Exception {
        context.stop();
    }



}
