package com.victor.stockalarms.filter;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.victor.stockalarms.filter.FilterConstants.*;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.stockalarms.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LogManager.getLogger(JWTAuthenticationFilter.class.getName());

    private static final String COULD_NOT_EXTRACT_CREDENTIALS_MESSAGE = "Could not extract credentials from http request";

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response) throws AuthenticationException {
        try {
            final UserDTO userDTO = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(),
                            userDTO.getPassword(),
                            new ArrayList<>())
            );
        } catch (final IOException e) {
            LOG.error(COULD_NOT_EXTRACT_CREDENTIALS_MESSAGE, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest req,
                                            final HttpServletResponse res,
                                            final FilterChain chain,
                                            final Authentication auth) {
        final String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(AUTHORIZATION_HEADER, BEARER_TOKEN_PREFIX + token);
    }

}