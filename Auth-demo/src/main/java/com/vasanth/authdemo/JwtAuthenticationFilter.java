package com.vasanth.authdemo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JwtAuthenticationFilter extends AuthenticationFilter {
    public JwtAuthenticationFilter(JwtAuthenticationManager authenticationManager) {
        super(authenticationManager, new JwtAuthenticationConverter());
        this.setSuccessHandler(((request, response, authentication) ->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }));
    }
}
