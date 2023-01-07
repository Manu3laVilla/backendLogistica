package com.example.backendlogistica.security.jwt;

import com.example.backendlogistica.security.entities.UsuarioPpal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UsuarioPpal usuarioPpal = (UsuarioPpal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPpal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }


    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch (MalformedJwtException e){
            logger.error("Token Mal Formado");
        }catch (UnsupportedJwtException e){
            logger.error("Token No Soportado");
        }catch (IllegalArgumentException e) {
            logger.error("Token Vacío");
        }catch (ExpiredJwtException e){
            logger.error("Token Expirado");
        }catch (SignatureException e){
            logger.error("Error En La Firma");
        }
        return false;
    }

}
