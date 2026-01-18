package com.worldinterlux.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // desativa CSRF no estilo Spring Security 6
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // libera todos endpoints
        return http.build();
    }
}
