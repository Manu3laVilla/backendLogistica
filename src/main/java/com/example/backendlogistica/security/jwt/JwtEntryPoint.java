package com.example.backendlogistica.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Error En El Método commence: " + authException.getMessage());
        res.resetBuffer();
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setHeader("Content-Type", "application/json");
        res.getOutputStream().print("{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"No Esta Autorizado\"}");
        res.flushBuffer();
    }
}
