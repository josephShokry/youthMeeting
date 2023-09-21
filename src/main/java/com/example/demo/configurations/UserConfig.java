package com.example.demo.configurations;

import org.springframework.stereotype.Component;

@Component
public class UserConfig {
    public static final String DATABASEUSERNAME = "root";
    public static final String DATABASEPASSWORD = "joseph";
    public static final String DATABASEURL = "jdbc:mysql://localhost:3306/church";
    public static final String DATABASEDRIVERCLASS = "com.mysql.cj.jdbc.Driver";

    private UserConfig() {
    }
}

