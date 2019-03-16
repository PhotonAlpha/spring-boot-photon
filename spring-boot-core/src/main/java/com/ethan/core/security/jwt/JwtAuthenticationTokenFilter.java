/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.jwt;

import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 19:08
 **/
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private UserDetailsService userService;
    @Autowired
    private JwtTokenUtils tokenUtils;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(tokenHeader);

        String username = null;
        String authToken = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            try {
                username = tokenUtils.getUsernameFromToken(authToken);
            } catch (Exception e) {
                logger.error("the token is expired and not valid anymore", e);
                e.printStackTrace();
            }
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            if(tokenUtils.validateToken(authToken, userDetails)) {
                JwtUser jwtUser = ((JwtUser) userDetails);
                jwtUser.setUsername(jwtUser.getMobileNo());
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
