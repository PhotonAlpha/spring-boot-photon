/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 15:51
 **/
public class JwtUser implements UserDetails {
    private final Long id;
    private String username;
    private final String password;
    private String mobileNo;
    private final String mobileCode;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public JwtUser(Long id, String username, String password, String mobileNo, String mobileCode, Collection<? extends GrantedAuthority> authorities, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobileNo = mobileNo;
        this.mobileCode = mobileCode;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
