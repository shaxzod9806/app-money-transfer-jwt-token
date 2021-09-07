package uz.pdp.app_jwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    static String secretKey = "This is my secret key";
    static long expiredTime = 86_400_000;

    public String generateToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + expiredTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}
