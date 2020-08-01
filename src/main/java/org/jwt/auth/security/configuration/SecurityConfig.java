package org.jwt.auth.security.configuration;

import lombok.RequiredArgsConstructor;
import org.jwt.auth.security.exception.CustomAuthenticationEntryPoint;
import org.jwt.auth.security.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailService customUserDetailService;
    private final CustomAuthenticationEntryPoint unauthorizedHandler;
    private final CustomAuthenticationFilter customAuthenticationFilter;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
    }

    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();

        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
