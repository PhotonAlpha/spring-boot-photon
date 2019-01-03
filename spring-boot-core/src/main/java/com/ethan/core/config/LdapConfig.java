/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-27 18:23
 **/
@Component
@ConfigurationProperties(prefix = "spring.ldap")
@Data
public class LdapConfig {
    private String[] urls;
    private String username;
    private String password;
    private String base;

    private String timeout;
    private String ssl;
    private String referral;
}
