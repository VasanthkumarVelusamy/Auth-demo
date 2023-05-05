package com.vasanth.authdemo.auth;

import com.vasanth.authdemo.JwtAuthenticationFilter;
import com.vasanth.authdemo.JwtAuthenticationManager;
import com.vasanth.authdemo.user.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtService jwtService, UsersService usersService) {
        this.jwtAuthenticationFilter = new JwtAuthenticationFilter (
                new JwtAuthenticationManager(jwtService, usersService)
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/about").permitAll()
                .requestMatchers(HttpMethod.POST, "/user", "/user/login").permitAll()
                .requestMatchers("/*/**").authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
