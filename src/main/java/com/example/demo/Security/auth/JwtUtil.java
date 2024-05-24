//https://medium.com/code-with-farhan/spring-security-jwt-authentication-authorization-a2c6860be3cf

package com.example.demo.Security.auth;

import com.example.demo.pojo.UserLoginDTO;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {


    private final String secret_key = "787878787878787878787878787878787878787878787878787878787878787878787878787878787878787878787878787878787878787878";
    private long accessTokenValidity = 60*60*1000;

    private final JwtParser jwtParser=Jwts.parserBuilder().setSigningKey(secret_key.getBytes(StandardCharsets.UTF_8)).build();
    //前后端约定？
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";


    /**
     * 根据userlogindto，创建token
     * @param user
     * @return
     */
    public String createToken(UserLoginDTO user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        claims.setExpiration(tokenValidity);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    /**
     * 根据token解析出claims
     * @param token
     * @return
     */
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * 根据HttpServletRequest，获得token，然后再获得claims
     * @param req
     * @return
     */
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            //调用resolveToken获得token
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            //捕获token过期异常
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            //捕获无效异常
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    /**
     * 根据http解析token
     * @param request
     * @return
     */
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getUsername(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }


}