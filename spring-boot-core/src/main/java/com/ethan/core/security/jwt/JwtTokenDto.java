/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
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
