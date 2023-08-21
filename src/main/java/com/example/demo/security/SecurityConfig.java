package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}

//authorities
/*
    ++ no one can access the details of a specific youth just the servant of the same family of the youth
    -- no one can edit the details of a specific youth just the servant of the same family of the youth


 */

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

.requestMatchers("/youth/**").access("@roleChecker.check(authentication,'admin')")//.hasRole("ADMIN")
.requestMatchers("/area/**").access("@roleChecker.check(authentication,'user')")//.hasAnyRole("ADMIN","USER")
.requestMatchers("/servant/**").access("@roleChecker.check(authentication,'admin')")
.formLogin(Customizer.withDefaults());

.requestMatchers("/youth/get").access("@roleChecker.sameFamily(authentication, T(java.lang.Integer).valueOf(request.getParameter('youthId')))")
.requestMatchers("/youth/edit").access("@roleChecker.sameFamily(authentication, request)")
 */



