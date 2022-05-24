package com.example.odbcapi.controller;

import com.example.odbcapi.value.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

@Slf4j
public class BaseController {

    @Autowired
    private Jwt jwt;

    @Value("${token.app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.secret-password}")
    private String password;

    public void validate(String token) throws Exception {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token.substring(7)).getBody();
            if (!claims.getSubject().equals(password)) {
                throw new Exception("Unauthorized");
            }
        }
    }

    public String genToken(String password) {
        return jwt.generateJwtToken(password, 10);
    }

}
