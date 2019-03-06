/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.controller;

import com.ethan.common.utils.Utils;
import com.ethan.core.constant.ServiceConstant;
import com.ethan.core.model.Users;
import com.ethan.core.security.jwt.JwtAuthenticationRequest;
import com.ethan.core.security.jwt.JwtTokenDto;
import com.ethan.core.security.jwt.JwtTokenUtils;
import com.ethan.core.security.jwt.JwtUser;
import com.ethan.core.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-18 10:27
 **/
@Slf4j
@RestController
@RequestMapping("api")
@ApiIgnore
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @GetMapping(value = "/verify/{mobileNo}")
    public ResponseEntity<SimpleResponse> smsVerify(@PathVariable("mobileNo") String mobileNo, Device device) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobileNo);
        if (userDetails != null) {
            String code = userService.preVerifyCode(mobileNo, device);
            return ResponseEntity.ok(new SimpleResponse(true, code));
        }
        return ResponseEntity.badRequest().body(new SimpleResponse(false, "code may expired."));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> smsRegister(@RequestBody @Valid JwtAuthenticationRequest authRequest, Device device) throws Exception {
        Users result = userService.register(authRequest, device);
        return ResponseEntity.ok(new SimpleResponse(true, result.getUsername()+"-"+jwtTokenUtil.getUsernameFromToken(result.getMobileCode())));
    }

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
            JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.canTokenBeRefreshed(token)) {
                String refreshToken = jwtTokenUtil.refreshToken(token);
                final String newToken = jwtTokenUtil.generateToken(user, device);
                return ResponseEntity.ok(new JwtTokenDto(newToken, refreshToken));
            }
        }
        return ResponseEntity.badRequest().body("Token maybe expired.");
    }
}

@Data
@AllArgsConstructor
class SimpleResponse {
    private Boolean success;
    private String message;
}

