/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-18 10:32
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -4200488784505744588L;
    @NotEmpty
    private String username;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String code;
}
