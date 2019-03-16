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
    public String preVerifyCode(String mobileNo, Device device) throws Exception {
        String code = TimeProvider.digitalGenerator6();
        log.info("generated code {} {}", code);

        String token = jwtTokenUtil.generateTempToken(code, device);

        Date now =TimeProvider.now();
        Authoritys auth = authoritiesDao.findByName(AuthorityName.ROLE_USER);
        if (auth == null) {
            throw new Exception("未知错误。");
        }
        auth.setUsers(null);

        Users newUser = Users.builder().username(TimeProvider.getUUID()).enabled(true)
                .mobileNo(mobileNo).mobileCode(token)
                .createTime(now).updateTime(now)
                .authorities(Arrays.asList(auth)).build();
        userDao.save(newUser);
        return code;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Users register(JwtAuthenticationRequest request, Device device) throws Exception {
        String code = request.getCode();
        log.info("generated code {} {}", code);
        // 1. verify password match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new Exception("密码不匹配。");
        }
        Users users = userDao.findByMobileNo(request.getUsername());
        if (users == null) {
            throw new Exception("用户不存在，请先注册。");
        }
        try {
            String tokenCode = jwtTokenUtil.getUsernameFromToken(users.getMobileCode());
            if (!code.equals(tokenCode)) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("验证码错误或者过期。");
        }
        Date now =TimeProvider.now();
        String password = TimeProvider.passwordEncoder().encode(request.getPassword());
        users.setPassword(password);
        users.setUpdateTime(now);
        users.setLoginTime(now);

        userDao.save(users);
        return users;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(JwtAuthenticationRequest request, Device device) throws Exception {
        Date now =TimeProvider.now();
        Users users = userDao.findByMobileNo(request.getUsername());
        boolean matched = request.getPassword().equals(request.getConfirmPassword());
        if (!matched) {
            return matched;
        }
        matched = TimeProvider.passwordEncoder().matches(request.getOldPassword(), users.getPassword());
        if (!matched) {
            return matched;
        }
        String password = TimeProvider.passwordEncoder().encode(request.getPassword());
        users.setPassword(password);
        users.setUpdateTime(now);
        userDao.save(users);
        return true;
    }
}
