/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.core.config;

import com.ethan.core.providers.CustomerEncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-10-05 10:02
 **/
@Configuration
@EnableEncryptableProperties
public class SecurityConfig {
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        String password = "loooooooooogpassword";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setAlgorithm("PBEWITHSHA256AND256BITAES-CBC-BC");
        encryptor.setPassword(password);
        encryptor.setStringOutputType("base64");
        return encryptor;
    }
    @Bean(name = "encryptablePropertyDetector")
    public EncryptablePropertyDetector encryptablePropertyDetector() {
        return new CustomerEncryptablePropertyDetector();
    }
}
