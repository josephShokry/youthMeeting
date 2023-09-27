package com.example.demo.configurations;

import org.springframework.stereotype.Component;

@Component
public class UserConfig {
    // this class not used till now, but I will refactor the properties to use spring config bean to be able to
    // use these vars in
    // this file will be filled from the user of the system to add his config that will make the system boot
    public static final String DATABASE_USER_NAME = "root";

    public static final String DATABASE_PASSWORD = "joseph";

    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/church";

    public static final String DATABASE_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private UserConfig() {
    }
}

