/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.encrypt;

import com.ethan.CommonApplication;
import com.ethan.core.config.SecurityConfig;
import com.ethan.core.config.WebSecurityConfiguration;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-18 10:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class PasswordEncryptTest {
    @Autowired
    private WebSecurityConfiguration configuration;
    @Autowired
    private SecurityConfig securityConfig;
    @Value("${jwt.secret}")
    public String pwd;

    @Test
    public void jasyptTets() {
        StringEncryptor encryptor = securityConfig.stringEncryptor();
        final String encryptStr = encryptor.encrypt("xxxx_Token_PasswOrd");
        final String decryptStr = encryptor.decrypt(encryptStr);
        System.out.println(encryptStr);
        System.out.println(decryptStr);
        System.out.println(">>>>>>>>> "+pwd);
    }

}
