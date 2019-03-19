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
public class UserPwdConfirmRequestDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String oldPwd;
    @NotEmpty
    private String newPwd;
    @NotEmpty
    private String confirmPwd;

}
