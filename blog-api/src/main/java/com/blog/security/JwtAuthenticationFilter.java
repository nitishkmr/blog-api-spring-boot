package com.blog.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Get Token
        String requestToken = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
            try{
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch(IllegalArgumentException e) {
                System.out.println(e);
            } catch(ExpiredJwtException e) {
                System.out.println("Jwt token has expired");
            } catch(MalformedJwtException e) {
                System.out.println("Invalid Jwt");
            }
        } else {
            System.out.println("Token doesn't begin with Bearer");
        }

        // 2. Once username is received, validate it
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            /*
             if token is valid configure Spring Security to manually set
             authentication
             */
            if(this.jwtTokenHelper.validateToken(token, userDetails)) {
                // 3. If valid, then set the Authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                /*
                 After setting the Authentication in the context, we specify
                 that the current user is authenticated. So it passes the
                 Spring Security Configurations successfully.
                 */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid Jwt");
            }
        } else {
            System.out.println("Username is null or context is not null");
        }

        // SecurityContext-Authentication would be checked if it's been set or not
        filterChain.doFilter(request, response);
    }
}
