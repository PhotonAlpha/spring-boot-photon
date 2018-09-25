/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.security.jwt;

import com.ethan.model.Authoritys;
import com.ethan.model.LdapUsers;
import com.ethan.model.Users;
import com.ethan.security.ldap.JwtLdapUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 15:47
 **/
public class JwtUserFactory {
    public static JwtUser create(Users user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getEmail(),
                mapToGeneratedAuthorities(user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }
    private static List<GrantedAuthority> mapToGeneratedAuthorities(List<Authoritys> authoritys) {
        List<GrantedAuthority> list = authoritys.stream().map(auth -> new SimpleGrantedAuthority(auth.getName().name()))
                .collect(Collectors.toList());
        return list;
    }
    public static JwtLdapUser create(LdapUsers user) {
        return new JwtLdapUser(
                user.getDn(),
                user.getUsername(),
                user.getPassword(),
                user.getCommonname(),
                user.getSurname(),
                user.getEmail(),
                stringToGeneratedAuthorities(user.getAuthorities()),
                true
        );
    }
    private static List<GrantedAuthority> stringToGeneratedAuthorities(List<String> authoritys) {
        if (authoritys == null) {
            throw new BadCredentialsException("Invalid Authority. ");
        }
        List<GrantedAuthority> list = authoritys.stream().map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
        return list;
    }
}
