/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-22 00:26
 **/
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class LdapUsers {
    private String dn;
    private String username;
    private String password;
    private String surname;
    private String commonname;
    private String email;
    private List<String> authorities;

}
