/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import javax.naming.ldap.LdapContext;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-21 16:23
 **/
@Configuration
public class LdapSourceConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LdapContextSource ldapContextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(environment.getRequiredProperty("spring.ldap.urls"));
        contextSource.setBase(environment.getRequiredProperty("spring.ldap.base"));
        contextSource.setUserDn(environment.getRequiredProperty("spring.ldap.username"));
        contextSource.setPassword(environment.getRequiredProperty("spring.ldap.password"));
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(ldapContextSource());
    }
}
