package com.security.practice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {
    private final List<UserDetails> user;

    public InMemoryUserDetailsService(List<UserDetails> user) {
        this.user = user;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return user.stream()
                .filter( // 사용자의 목록에서 요청된 사용자 이름과 일치하는 항목을 필터링
                        u->u.getUsername().equals(username)
                ).findFirst() // 일치하는 사용자가 있으면 반환
                .orElseThrow( // 이 사용자 이름이 존재하지 않으면 예외를 던진다.
                        ()->new UsernameNotFoundException("User not found")
                );
    }
}
