package com.example.demo;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class Demo6ApplicationTests {

    @Test
    void contextLoads() {
        JwtBuilder builder = Jwts.builder();
        String compact = builder
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("username", "obama")
                .claim("role", "admin")
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, "admin")
                .compact();
    }

}
