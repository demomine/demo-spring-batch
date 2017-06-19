package com.lance.demo.spring.batch.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by perdonare on 2017/5/10.
 */
@Configuration
@MapperScan(basePackages = {"com.lance.demo.spring.dao.mapper"})
public class MybatisConfig {

    private DataSource dataSource;
    private ResourceLoader resourceLoader;

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.1.72:3306/batch?useUnicode=true&characterEncoding=utf-8&useSSL=false");//防止乱码
        dataSource.setUsername("root");
        dataSource.setPassword("xb-12345");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(50);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/lance/demo/spring/dao/mapper/*.xml"));
        return sessionFactory;
    }

    //@PostConstruct
    public void setupJobSchema() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(this.resourceLoader.getResource("org/springframework/batch/core/schema-drop-mysql.sql"));
        populator.addScript(this.resourceLoader.getResource("org/springframework/batch/core/schema-mysql.sql"));
        populator.setContinueOnError(true);
        DatabasePopulatorUtils.execute(populator, this.dataSource);
    }
}
