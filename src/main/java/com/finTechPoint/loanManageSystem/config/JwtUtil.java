package com.finTechPoint.loanManageSystem.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.stereotype.Component;
@Component
public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
        "your-very-secure-and-long-secret-key-1234567890123456".getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24)) // 1 day
                .signWith(SECRET_KEY)
                .compact();
    }
}
