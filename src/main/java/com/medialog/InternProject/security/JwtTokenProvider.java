package com.medialog.InternProject.security;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {


    private final String secretKey ="medialogmedialogmedialogmedialogmedialogmedialogmedialog";
    private final long validityInMilliseconds=3600000;


    @Override
    public String createToken(String subject) {

        Date now = new Date();

        Date validity = new Date(now.getTime()
                + validityInMilliseconds);

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);

        Key signinKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(validity)
                .setSubject(subject)
                .signWith(signinKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            return isExpired(token) && checkSubject(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Boolean isExpired(String token) {
        return Jwts.parserBuilder()
                        .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                        .build()
                        .parseClaimsJws(token)
                        .getBody().getExpiration().before(new Date());
    }

    private boolean checkSubject(String token){
        return getSubject(token).equals("medialog");
    }
}