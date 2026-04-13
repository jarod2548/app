package org.app.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.app.Account.domain.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTService {
    private SecretKey jwtSecret =  Jwts.SIG.HS256.key().build();

    long expiration = 3600000;


    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .claim("ID", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(jwtSecret)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(jwtSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getRole(String token) {
        return (String) Jwts.parser().verifyWith(jwtSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role");
    }

    public UUID getID(String token) {
        String claim = (String) Jwts.parser()
                .verifyWith(jwtSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("ID");

        return UUID.fromString(claim);
    }

    public UserPrincipal getClaims(String token){
        Claims claims = Jwts.parser()
                .verifyWith(jwtSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();
        String role = (String) claims.get("role");
        UUID id = UUID.fromString((String) claims.get("ID"));

        return new UserPrincipal(id, username,role);
    }
}
