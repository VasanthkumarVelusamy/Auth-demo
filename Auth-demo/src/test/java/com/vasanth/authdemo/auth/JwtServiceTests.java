package com.vasanth.authdemo.auth;

import org.hibernate.AssertionFailure;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class JwtServiceTests {

    private JwtService jwtService() {
        return new JwtService();
    }

    @Test
    void test_createJwt_works_with_username() {
        var jwtService = jwtService();
        var jwtToken = jwtService.createJwt("vasanth");
        var username = jwtService.getUsernameFromJwt(jwtToken);
        assertEquals(null,"vasanth", username);
    }

    @Test
    void test_createJwt_errors_with_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            var jwtService = jwtService();
            jwtService.createJwt(null);
        });
    }
}
