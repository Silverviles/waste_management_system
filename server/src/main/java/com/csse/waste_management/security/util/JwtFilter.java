package com.csse.waste_management.security.util;

import com.csse.waste_management.security.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = Logger.getLogger(JwtFilter.class.getName());

    private final JwtUserDetailsService jwtUserDetailsService;
    private final TokenManager tokenManager;

    @Autowired
    public JwtFilter(JwtUserDetailsService jwtUserDetailsService, TokenManager tokenManager) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String token;

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7);

            try {
                username = tokenManager.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                LOGGER.warning("An error occurred during getting username from token.");
            } catch (ExpiredJwtException e) {
                LOGGER.warning("The token is expired and not valid anymore.");
            }
        } else {
            LOGGER.warning("Couldn't find bearer string, will ignore the header.");
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails credentials = jwtUserDetailsService.loadUserByUsername(username);

            if (tokenManager.validateJwtToken(token, credentials)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(credentials,
                        null, credentials.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register");
    }
}
