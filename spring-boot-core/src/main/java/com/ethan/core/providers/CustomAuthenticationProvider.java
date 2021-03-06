/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.providers;

import com.ethan.core.constant.ServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-23 16:28
 **/
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier(ServiceConstant.LDAP_SERVICE)
    private UserDetailsService userService;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        // logger.info("Authenticating for user: {} with password: {}", name, password);

        final UserDetails userDetails = userService.loadUserByUsername(name);
        if (userDetails == null || !matchPassword(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username and password combination");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public CustomAuthenticationProvider setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    private boolean matchPassword(String rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
