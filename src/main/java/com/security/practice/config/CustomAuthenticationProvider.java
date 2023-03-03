package com.security.practice.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    // 인증 논리를 추가할 위치
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Principal 인터페이스의 getName() 메서드를 Authentication에서 상속 받는다.
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        // 이 조건은 일반적으로 UserDetailsService 및 PasswordEncoder를 호출해서 사용자 이름과 암호를 테스트한다.
        if("jaeseon".equals(username) && "0070".equals(password)){
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        }else {
            throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Authentication 형식의 구현을 추가할 위치
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
