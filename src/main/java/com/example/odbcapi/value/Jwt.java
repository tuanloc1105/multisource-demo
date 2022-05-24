package com.example.odbcapi.value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jwt {

    @Value("${token.app.jwtSecret}")
    private String jwtSecret;

    @Value("${token.app.jwtExpiration}")
    private int jwtExpirationMs;

    @Value("${app.secret-password}")
    private String password;

    public String generateJwtToken(String password, int lastingDay) {
        if (!password.equals(this.password)) {
            return Strings.EMPTY;
        }
        return Jwts.builder()
                .setSubject(this.password)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + toMilliSeconds(jwtExpirationMs)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static int toMilliSeconds(int day) {
        return day * 24 * 60 * 60 * 1000;
    }

}
