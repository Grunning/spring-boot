package com.base;

import com.alibaba.druid.pool.DruidDataSource;
import com.denghb.dbhelper.DbHelper;
import com.denghb.dbhelper.impl.DbHelperImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Configuration
@PropertySource({ "classpath:config.properties" })
@ComponentScan(basePackages = {"com.base"},
        includeFilters = {@ComponentScan.Filter(value = Configuration.class, type =  FilterType.ANNOTATION),
                @ComponentScan.Filter(value = Controller.class, type =  FilterType.ANNOTATION)},
        excludeFilters = {@ComponentScan.Filter(value = Service.class, type =  FilterType.ANNOTATION)}
)
public class AppConfig {

    private static final Logger logger = Logger.getLogger(AppConfig.class);

    @Autowired
    public @Bean
    DruidDataSource dataSource(AppProperties appProperties) {
        logger.info("===========================初始化数据库连接池===========================");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(appProperties.getDriver());
        dataSource.setUrl(appProperties.getUrl());
        dataSource.setUsername(appProperties.getUser());
        dataSource.setPassword(appProperties.getPass());
        dataSource.setMaxActive(30);
        dataSource.setMaxWait(10000); //最大等待时间
        dataSource.setInitialSize(10);
        dataSource.setTimeBetweenEvictionRunsMillis(30000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    public @Bean JdbcTemplate jdbcTemplate(DruidDataSource dataSource) {
        logger.info("===========================初始化jdbcTemplate===========================");
        return new JdbcTemplate(dataSource);
    }

    public @Bean DbHelperImpl dbHelperImpl(JdbcTemplate jdbcTemplate) {
        return new DbHelperImpl(jdbcTemplate);
    }

}
