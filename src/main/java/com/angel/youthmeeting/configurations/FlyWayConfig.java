package com.angel.youthmeeting.configurations;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class FlyWayConfig {

    private final DataSource dataSource;

    public FlyWayConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    @Profile("app")
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
        return flyway;
    }
}
