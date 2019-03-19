/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/03/16
 */
package com.ethan.common.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterRequestDto {
    @NotEmpty
    private String mobileNo;
    @NotEmpty
    private String code;
    @NotEmpty
    private String password;
}
