package com.security.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class ProjectConfig{

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){

        UserDetails user = User.withUsername("jaeseon")
                .password("0070")
                .authorities("read")
                .passwordEncoder((Function<String, String>) NoOpPasswordEncoder.getInstance())
                .build();
        return new InMemoryUserDetailsManager(user);
            
    }
}
