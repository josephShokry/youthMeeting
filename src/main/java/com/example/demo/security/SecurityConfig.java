package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private DataSource dataSource;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("jo")) // Encode password using BCrypt
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("jo")) // Encode password using BCrypt
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(List.of(user, admin));
//    }

    @Bean
    public UserDetailsManager authenticateUsers() {

//        UserDetails user = User
//                .withUsername("username")
//                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"))
////                .roles("ADMIN");
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
////        users.setAuthoritiesByUsernameQuery("select user_name,user_pwd,user_enabled from user where user_name=?");
////        users.setUsersByUsernameQuery("select user_name,user_role from user where user_name=?");
//        users.createUser(user);
//        return users;

        PasswordEncoder passwordEncoder = passwordEncoder();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("pas"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("pas"))
//                .roles("ADMIN")
//                .build();
//
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(admin);

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/youth/**").access("@roleChecker.check(authentication,'admin')")//.hasRole("ADMIN")
                .requestMatchers("/area/**").access("@roleChecker.check(authentication,'user')")//.hasAnyRole("ADMIN","USER")
                .requestMatchers("/**").permitAll()
                .and()
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

}

/*

CREATE TABLE users (
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,

    PRIMARY KEY (username)
);

CREATE TABLE authorities (
    username VARCHAR(255) NOT NULL,
    authority VARCHAR(255) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

 */



