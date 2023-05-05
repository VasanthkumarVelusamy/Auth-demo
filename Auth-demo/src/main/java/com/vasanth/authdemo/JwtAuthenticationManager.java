package com.vasanth.authdemo;

import com.vasanth.authdemo.user.UsersService;
import com.vasanth.authdemo.auth.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationManager implements AuthenticationManager {

    private JwtService jwtService;
    private UsersService usersService;

    public JwtAuthenticationManager(JwtService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthentication) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
            String jwtString = jwtAuthentication.getCredentials();
            var username = jwtService.getUsernameFromJwt(jwtString);
            var user = usersService.findUserByUsername(username);
            jwtAuthentication.setUser(user);
            return jwtAuthentication;
        }
        return null;
    }
}
