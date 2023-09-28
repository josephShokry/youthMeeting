package com.angel.youthmeeting.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("app")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(UserConfig.APP_DATABASE_DRIVER_CLASS);
        dataSource.setUrl(UserConfig.APP_DATABASE_URL);
        dataSource.setUsername(UserConfig.APP_DATABASE_USER_NAME);
        dataSource.setPassword(UserConfig.APP_DATABASE_PASSWORD);
        return dataSource;
    }

//    @Bean
//    @Profile("test")
//    public DataSource dataSources() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(UserConfig.TEST_DATABASE_DRIVER_CLASS);
//        dataSource.setUrl(UserConfig.TEST_DATABASE_URL);
//        dataSource.setUsername(UserConfig.TEST_DATABASE_USER_NAME);
//        dataSource.setPassword(UserConfig.TEST_DATABASE_PASSWORD);
//        return dataSource;
//    }
}
