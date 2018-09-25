/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.encrypt;

import com.ethan.config.WebSecurityConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-18 10:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncryptTest {
    @Autowired
    private WebSecurityConfiguration configuration;

    @Test
    public void passwordTest() {
        PasswordEncoder encoder = configuration.passwordEncoder();
        encoder.encode("password");
    }
}
