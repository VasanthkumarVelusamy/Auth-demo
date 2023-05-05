package com.vasanth.authdemo;

import com.vasanth.authdemo.user.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private String jwtString;
    public UserResponseDto user;

    public JwtAuthentication(String jwtString) {
        this.jwtString = jwtString;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return jwtString;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return (user != null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
