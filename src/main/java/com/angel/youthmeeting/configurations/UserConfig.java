package com.angel.youthmeeting.configurations;

import org.springframework.stereotype.Component;

@Component
public class UserConfig {

    public static final String APP_DATABASE_USER_NAME = "root";

    public static final String APP_DATABASE_PASSWORD = "joseph";

    public static final String APP_DATABASE_URL = "jdbc:mysql://localhost:3306/church";

    public static final String APP_DATABASE_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    public static final String TEST_DATABASE_USER_NAME = "sa";

    public static final String TEST_DATABASE_PASSWORD = "";

    public static final String TEST_DATABASE_URL = "jdbc:h2:mem:testdb";

    public static final String TEST_DATABASE_DRIVER_CLASS = "org.h2.Driver";

    private UserConfig() {
    }
}

