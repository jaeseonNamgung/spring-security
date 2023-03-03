package com.security.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig{

    @Bean
    public UserDetailsService userDetailsService(){
        var userDetailsService = new InMemoryUserDetailsManager();
        var user = User.withUsername("jaeseon")
                .password("0070")
                .authorities("read")
                .build();
        userDetailsService.createUser(user);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // 암호에 암호화나 해시를 적용하지 않고 일반 텍스트 처럼 처리할 때 사용
        // 운영 단계에서는 절대 사용하면 안된다. (Deprecated 됨)
        return NoOpPasswordEncoder.getInstance();
    }

    // SpringSecurity 5.4 이후 부터 WebSecurityConfigurerAdapter Deprecated 됨
    // configurer -> SecurityFilterChain
    // authorizeRequests 메서드도 Deprecated 됨
    // authorizeRequests -> authorizeHttpRequest(()-> {});
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        // 모든 요청에 인증이 필요
        http.authorizeHttpRequests(authz->authz.anyRequest().authenticated());

        // 인즈 없이 요청
        // http.authorizeHttpRequests(authz->authz.anyRequest().permitAll());
        return http.build();
    }
}
