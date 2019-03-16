package com.ethan.core.service;

import com.ethan.core.model.Users;
import com.ethan.core.security.jwt.JwtAuthenticationRequest;
import org.springframework.mobile.device.Device;

import java.util.List;

/**
 * @Author Ethan Cao
 * @Description //TODO P0500066
 * @Date 11:11 AM 9/24/2018
 * @Param 
 * @return 
 **/
public interface UserService<T> {
    /**
     * fetch data by rule id
     * @return user list
     */
    List<T> getUsers();

    String preVerifyCode(String mobileNo, Device device) throws Exception;

    Users register(JwtAuthenticationRequest request, Device device) throws Exception;

    boolean updatePassword(JwtAuthenticationRequest request, Device device) throws Exception;
}
