/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.service.impl;

import com.ethan.constant.ServiceConstant;
import com.ethan.dao.UserDao;
import com.ethan.model.Users;
import com.ethan.security.jwt.JwtUserFactory;
import com.ethan.service.UserService;
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
    @Autowired
    private LdapTemplate ldapTemplate;

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
