package com.lakesidehotel.app.security.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.lakesidehotel.app.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {

    @Value("${jwt.secret.key}")
    private String secret;

    @Value("${jwt.token.issuer}")
    private String issuer;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(issuer,Algorithm.HMAC512(secret));
    }
}
