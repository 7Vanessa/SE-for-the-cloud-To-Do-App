package com.example.todoappauthservice.config;

import com.example.todoappauthservice.constants.UrlConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private String extractJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (!path.equals(UrlConstants.REGISTER_URL) && !path.equals(UrlConstants.LOGIN_URL)){
            String jwtToken = extractJwtFromRequest(request);

            if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)){
                String username = jwtTokenProvider.extractUsername(jwtToken);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, null);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                logger.debug("Invalid or missing JWT Token");
            }

            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
