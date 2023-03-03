package com.security.practice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class ProjectConfig{

    private final CustomAuthenticationProvider authenticationProvider;
    private AuthenticationManager authenticationManager;


    @Bean
    public CustomAuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder  authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        authenticationManager = authenticationManagerBuilder.build();

        http.httpBasic();
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        http.authenticationManager(authenticationManager);
        return http.build();
    }



}
