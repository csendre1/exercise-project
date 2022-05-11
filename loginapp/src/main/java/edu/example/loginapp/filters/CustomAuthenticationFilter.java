package edu.example.loginapp.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.example.loginapp.model.AuthUser;
import edu.example.loginapp.security.JwtTokenUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        AuthUser user = new AuthUser();
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), AuthUser.class);

            log.info("User with username : [ {} ] data retrieved from the body.", user.getUsername());

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()));
        } catch (BadCredentialsException exception) {
            log.error("User credentials for user: {} war incorrect.", user.getUsername());
            throw new AuthenticationServiceException(
                    "Bad credentials for user " + user.getUsername() + ", with exception: " + exception.getMessage());
        } catch (Exception e) {
            log.error("An exception occurred when mapping the request body to the desired object : {}.",
                    AuthUser.class.getName());
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        final User user = (User) authResult.getPrincipal();
        final String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtTokenUtils.EXPIRATION_DATE))
                .sign(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY.getBytes()));
        ;
        log.info("Successfully logged in for user : {} ", ((User) authResult.getPrincipal()).getUsername());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),
                Collections.singletonMap(JwtTokenUtils.ACCESS_TOKEN, accessToken));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        log.info("Failed to log in user.");
        response.sendError(HttpStatus.BAD_REQUEST.value());
        new ObjectMapper().writeValue(response.getOutputStream(),
                Collections.singletonMap(JwtTokenUtils.ERROR, failed.getMessage()));
    }

}
