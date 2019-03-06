/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.service.impl;

import com.ethan.core.dao.AuthoritiesDao;
import com.ethan.core.dao.UserDao;
import com.ethan.core.model.AuthorityName;
import com.ethan.core.model.Authoritys;
import com.ethan.core.model.Users;
import com.ethan.core.providers.TimeProvider;
import com.ethan.core.security.jwt.JwtAuthenticationRequest;
import com.ethan.core.security.jwt.JwtTokenUtils;
import com.ethan.core.security.jwt.JwtUserFactory;
import com.ethan.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 15:39
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthoritiesDao authoritiesDao;

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Override
    public List<Users> getUsers() {
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNo) throws UsernameNotFoundException {
        Users user = userDao.findByMobileNo(mobileNo);
        if(user != null) {
            return JwtUserFactory.create(user);
        }else {
            throw new UsernameNotFoundException(String.format("未注册，请先注册 '%s'.", mobileNo));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String preVerifyCode(String mobileNo, Device device) {
        String code = TimeProvider.digitalGenerator6();
        log.info("generated code {} {}", code);
        String token = jwtTokenUtil.generateTempToken(code, device);

        Users user = userDao.findByMobileNo(mobileNo);
        if (user != null) {
            user.setMobileCode(token);
            userDao.save(user);
            return code;
        } else {
            throw new UsernameNotFoundException(String.format("未注册，请先注册 '%s'.", mobileNo));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Users register(JwtAuthenticationRequest request, Device device) throws Exception {
        String code = request.getCode();
        log.info("generated code {} {}", code);
        String token = jwtTokenUtil.generateTempToken(code, device);
        Date now =TimeProvider.now();
        Authoritys auth = authoritiesDao.findByName(AuthorityName.ROLE_USER);
        auth.setUsers(null);
        if (auth == null) {
            throw new Exception("未知错误。");
        }

        Users newUser = Users.builder().username(TimeProvider.getUUID()).password(request.getPassword()).enabled(true)
                .mobileNo(request.getUsername()).mobileCode(token)
                .loginTime(now).createTime(now).updateTime(now)
                .authorities(Arrays.asList(auth)).build();
        userDao.save(newUser);
        return newUser;
    }
}
