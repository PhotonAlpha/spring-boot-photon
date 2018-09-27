/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import java.util.HashMap;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-21 16:23
 **/
@Configuration
public class LdapSourceConfig {
    @Autowired
    private LdapConfig ldapConfig;

    @Bean
    public LdapContextSource ldapContextSource() {
        HashMap<String, Object> baseEnv = new HashMap<String, Object>();
        baseEnv.put("com.sun.jndi.ldap.connect.timeout", ldapConfig.getTimeout());

        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrls(ldapConfig.getUrls());
        contextSource.setBase(ldapConfig.getBase());
        contextSource.setUserDn(ldapConfig.getUsername());
        contextSource.setPassword(ldapConfig.getPassword());
        contextSource.setReferral(ldapConfig.getReferral());
        contextSource.setBaseEnvironmentProperties(baseEnv);
        contextSource.afterPropertiesSet();
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(ldapContextSource());
    }
}
