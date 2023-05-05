package com.vasanth.authdemo.auth;

import com.vasanth.authdemo.user.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String createToken(UserEntity user) {
        AuthTokenEntity tokenEntity = new AuthTokenEntity();
        tokenEntity.setUser(user);
        return authRepository.save(tokenEntity).getToken().toString();
    }

    public UserEntity getUser(String token) {
        AuthTokenEntity tokenEntity = authRepository.findById(token).orElseThrow();
        return tokenEntity.getUser();
    }
}
