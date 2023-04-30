package com.vasanth.authdemo.user.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    public static final String SECRET = "LBuybIUbilauBILUBVILUBLI8I899283Y893246ILUB8B(*G98B98SDF8DS8";
    Algorithm algorithm= Algorithm.HMAC256(SECRET);
    public String createJwt(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public String getUsernameFromJwt(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }
}
