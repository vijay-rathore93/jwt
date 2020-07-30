package org.jwt.auth.security.configuration;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.security.service.CustomUserDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService CustomUserDetailService;
    private final PasswordEncoder passwordEncoder;
}
