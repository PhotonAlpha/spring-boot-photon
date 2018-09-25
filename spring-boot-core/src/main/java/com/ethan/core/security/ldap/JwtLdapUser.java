/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.ldap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import java.util.Collection;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-24 10:36
 **/
public class JwtLdapUser implements LdapUserDetails {
    private final String dn;
    private final String username;
    private String password;
    private final String commonname;
    private final String surname;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public JwtLdapUser(String dn, String username, String password, String commonname, String surname, String email, Collection<? extends GrantedAuthority> authorities, boolean enabled) {
        this.dn = dn;
        this.username = username;
        this.password = password;
        this.commonname = commonname;
        this.surname = surname;
        this.email = email;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public String getCommonname() {
        return commonname;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getDn() {
        return dn;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
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
        return true;
    }

}
