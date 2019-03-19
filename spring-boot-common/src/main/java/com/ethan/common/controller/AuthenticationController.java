/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.controller;

import com.ethan.common.model.dto.request.RegisterRequestDto;
import com.ethan.common.model.dto.request.UserPwdConfirmRequestDto;
import com.ethan.common.model.dto.response.SimpleResponse;
import com.ethan.core.model.Users;
import com.ethan.core.security.jwt.JwtAuthenticationRequest;
import com.ethan.core.security.jwt.JwtTokenDto;
import com.ethan.core.security.jwt.JwtTokenUtils;
import com.ethan.core.security.jwt.JwtUser;
import com.ethan.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.security.Principal;

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
    public ResponseEntity<SimpleResponse> smsVerify(@PathVariable("mobileNo") String mobileNo, Device device) throws Exception {
        String code = userService.preVerifyCode(mobileNo, device);
        return ResponseEntity.ok(new SimpleResponse(true, code));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> smsRegister(@RequestBody @Valid RegisterRequestDto dto, Device device) throws Exception {
        JwtAuthenticationRequest authRequest = new JwtAuthenticationRequest();
        authRequest.setUsername(dto.getMobileNo());
        authRequest.setPassword(dto.getPassword());
        authRequest.setConfirmPassword(dto.getPassword());
        authRequest.setCode(dto.getCode());
        Users result = userService.register(authRequest, device);
        return ResponseEntity.ok(new SimpleResponse(true, result.getUsername()+"-"+jwtTokenUtil.getUsernameFromToken(result.getMobileCode())));
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<JwtTokenDto> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest, Device device) {
        //TODO
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenUtil.generateWebsocketToken(authRequest.getCode(), (JwtUser)userDetails, device);
        return ResponseEntity.ok(new JwtTokenDto(token));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtTokenDto> webloginRequest(@RequestBody JwtAuthenticationRequest authRequest, Device device) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        JwtUser userDetails = (JwtUser)userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenUtil.generateWebToken(userDetails, device);
        return ResponseEntity.ok(new JwtTokenDto(token));
    }

    @PostMapping(value = "/updatePwd")
    public ResponseEntity<?> updatePassword(HttpServletRequest request, @RequestBody @Valid UserPwdConfirmRequestDto dto, Device device) throws Exception {
        Principal principal = request.getUserPrincipal();
        String userId = principal.getName();
        JwtAuthenticationRequest entity = new JwtAuthenticationRequest();
        entity.setUsername(userId);
        entity.setOldPassword(dto.getOldPwd());
        entity.setPassword(dto.getNewPwd());
        entity.setConfirmPassword(dto.getConfirmPwd());
        boolean success = userService.updatePassword(entity, device);
        return ResponseEntity.ok(new SimpleResponse(success, ""));
    }
}

