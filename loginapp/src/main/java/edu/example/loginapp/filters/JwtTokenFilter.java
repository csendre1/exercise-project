package edu.example.loginapp.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.example.loginapp.security.JwtTokenUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (checkValidHeader(header)) {
            try {
                SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(header));
                log.info("Successfully validate token for the [{}] request", request.getServletPath());
            } catch (Exception exception) {
                log.error("Exception occurred when validation the token. for the [{}] request.Exception: {}",
                        request.getServletPath(), exception.getMessage());
                new ObjectMapper().writeValue(response.getOutputStream(),
                        Collections.singletonMap("error", exception.getMessage()));
            }
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(final String header) {
        final String token = header.replace(JwtTokenUtils.BEARER, "");
        final JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY.getBytes())).build();
        final DecodedJWT decodedJWT = verifier.verify(token);
        final String username = decodedJWT.getSubject();
        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }

    private boolean checkValidHeader(final String header) {
        return StringUtils.hasLength(header) && header.startsWith(JwtTokenUtils.BEARER);
    }

}
