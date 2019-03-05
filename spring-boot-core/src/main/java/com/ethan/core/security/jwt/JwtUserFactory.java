/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.jwt;

import com.ethan.core.model.Authoritys;
import com.ethan.core.model.Users;
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
                user.getPassword(),
                user.getMobileNo(),
                user.getMobileCode(),
                mapToGeneratedAuthorities(user.getAuthorities()),
                user.getEnabled()
        );
    }
    private static List<GrantedAuthority> mapToGeneratedAuthorities(List<Authoritys> authorities) {
        List<GrantedAuthority> list = authorities.stream().map(auth -> new SimpleGrantedAuthority(auth.getName().name()))
                .collect(Collectors.toList());
        return list;
    }
}
