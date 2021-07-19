package io.realworld.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider implements InitializingBean {
    private final String secret;
    private final long tokenValidityMilliseconds;
    private Key key;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityMilliseconds) {
        this.secret = secret;
        this.tokenValidityMilliseconds = tokenValidityMilliseconds;
    }

    @Override
    public void afterPropertiesSet() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + this.tokenValidityMilliseconds * 1000))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(claims.getSubject(), "", Collections.emptyList());

        return new UsernamePasswordAuthenticationToken(principal, token, Collections.emptyList());
    }

    public boolean validateToken(String token) {
        /*
            UnsupportedJwtException – if the claimsJws argument does not represent an Claims JWS
            MalformedJwtException – if the claimsJws string is not a valid JWS
            SignatureException – if the claimsJws JWS signature validation fails -> SecurityException
            ExpiredJwtException – if the specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.
            IllegalArgumentException – if the claimsJws string is null or empty or only whitespace
         */
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgument JWT token.");
        }

        return false;
    }
}
