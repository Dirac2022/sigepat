/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/swagger-ui/**", "/v3/api-docs/**", "/api/v1/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/api/auth/success", true) // Redirección tras éxito
                .failureUrl("/api/auth/failure") // Redirección tras fallo
            );

        //.requestMatchers("/api/auth/**").permitAll()
        //.requestMatchers("/", "/public/**").permitAll()
        //.requestMatchers("/", "/index.html", "/swagger-ui/**").permitAll()
        // .requestMatchers("/", "/index.html").permitAll()
        return http.build();
    }
}
