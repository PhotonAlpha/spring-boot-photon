/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.encrypt;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-24 13:15
 **/
public class MainTest {
    @Test
    public void testEncrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result1 = encoder.encode("password");
        System.out.println(result1);
        LdapShaPasswordEncoder shaencoder = new LdapShaPasswordEncoder();
        String result2 = shaencoder.encode("password");
        boolean is = shaencoder.matches("password", "{SHA}W6ph5Mm5Pz8GgiULbPgzG37mj9g=");
        System.out.println(result2);
        System.out.println(is);
    }
}
