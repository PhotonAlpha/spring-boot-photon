/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.security.jwt;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-20 13:50
 **/
@Getter
@Setter
public class JwtTokenDto {
    private String authToken;
    private String refreshToken;

    public JwtTokenDto(String authToken) {
        this.authToken = authToken;
    }

    public JwtTokenDto(String authToken, String refreshToken) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
    }
}
