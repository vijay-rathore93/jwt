package org.jwt.auth.security.configuration;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Slf4j
public class CustomTokenProvider {

    @Value("${app.jwtSecret}")
    private String secretKey;

    @Value("${app.jwtExpirationInMs}")
    private String expirationTime;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder().
                setSubject(userPrinciple.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }


    public String getJwtFromRequest(HttpServletRequest request) {
        String jwtToken=null;
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            jwtToken= bearerToken.substring(7, bearerToken.length());
        }
        return jwtToken;
    }
}
