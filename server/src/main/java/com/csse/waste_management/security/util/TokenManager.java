package com.csse.waste_management.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Serial
    private static final long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateJwtToken(UserDetails credentials) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims().add(claims).and()
                .subject(credentials.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getKey())
                .compact();
    }

    public Boolean validateJwtToken(String token, UserDetails credentials) {
        final String username = getUsernameFromToken(token);
        boolean isTokenExpired = getAllClaimsFromToken(token).getExpiration().before(new Date());
        return (username.equals(credentials.getUsername()) && !isTokenExpired);
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
