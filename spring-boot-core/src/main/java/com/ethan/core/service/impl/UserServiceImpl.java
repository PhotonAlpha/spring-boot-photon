/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.service.impl;

import com.ethan.core.constant.ServiceConstant;
import com.ethan.core.dao.UserDao;
import com.ethan.core.model.Users;
import com.ethan.core.security.jwt.JwtUserFactory;
import com.ethan.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 15:39
 **/
@Service(ServiceConstant.DB_SERVICE)
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<Users> getUsers() {
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userDao.findByUsername(username);
        if(user != null) {
            return JwtUserFactory.create(user);
        }else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }

}
