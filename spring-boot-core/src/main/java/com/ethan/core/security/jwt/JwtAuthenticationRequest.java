/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-18 10:32
 **/
@Getter
@Setter
@ToString
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -4200488784505744588L;
    private String username;
    private String password;
}
