package com.ethan.common.model.dto.request;

import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @date 05/03/2019
 */
@Data
@ToString
public class FcAppInstallRequestDto {
    private Long deviceId;
    private Long appId;

}
