package com.lakesidehotel.app.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Date;
@AllArgsConstructor
public class JwtUtil {
    private String issuer;
    private Algorithm algorithm;
    public String generateAccessToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("roles", userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withSubject(userDetails.getUsername())
                .sign(algorithm);
    }
}
