/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.controller;

import com.ethan.core.constant.ServiceConstant;
import com.ethan.core.security.jwt.JwtAuthenticationRequest;
import com.ethan.core.security.jwt.JwtTokenDto;
import com.ethan.core.security.jwt.JwtTokenUtils;
import com.ethan.core.security.jwt.JwtUser;
import com.ethan.core.security.ldap.JwtLdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-18 10:27
 **/
@RestController
@RequestMapping("api")
@ApiIgnore
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    @Qualifier(ServiceConstant.LDAP_SERVICE)
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest, Device device) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails, device);
        return ResponseEntity.ok(new JwtTokenDto(token, refreshToken));
    }

    @GetMapping(value = "/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, Device device) {
        final String requestHeader = request.getHeader(tokenHeader);
        System.out.println("requestHeader" + requestHeader);
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            final String token = requestHeader.substring(7);
            final String username = jwtTokenUtil.getUsernameFromToken(token);
            JwtLdapUser user = (JwtLdapUser) userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.canTokenBeRefreshed(token)) {
                String refreshToken = jwtTokenUtil.refreshToken(token);
                final String newToken = jwtTokenUtil.generateToken(user, device);
                return ResponseEntity.ok(new JwtTokenDto(newToken, refreshToken));
            }
        }
        return ResponseEntity.badRequest().body("Token maybe expired.");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
