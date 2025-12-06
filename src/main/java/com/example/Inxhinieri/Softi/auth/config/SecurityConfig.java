package com.example.Inxhinieri.Softi.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())

                // 2. Allow ALL requests without authentication
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

                // 3. Disable HTTP Basic (The most common source of the 401 challenge)
                .httpBasic(httpBasic -> httpBasic.disable())

                // 4. Disable Form Login
                .formLogin(formLogin -> formLogin.disable());

        return http.build();
    }
}