/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethon.encrypt;

import com.ethan.CoreApplication;
import com.ethan.constant.ServiceConstant;
import com.ethan.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-21 16:39
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class LdapTest {
    @Autowired
    @Qualifier(ServiceConstant.LDAP_SERVICE)
    private UserService userService;

    @Test
    public void getAllUsers() {
        userService.getUsers();
    }
}
