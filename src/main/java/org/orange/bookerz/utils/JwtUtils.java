package org.orange.bookerz.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.orange.bookerz.constant.SecurityConstants;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {
    private JwtUtils() {}
    public static String generateToken(Long memberId,String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", role);
        claims.put("username", username);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(memberId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간
                .signWith(getSigningKey())
                .compact();
    }

    private static Key getSigningKey() {
        byte[] keyBytes = SecurityConstants.JWT_KEY.getValue().getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}
