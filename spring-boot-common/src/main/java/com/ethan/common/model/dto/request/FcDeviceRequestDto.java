package com.ethan.common.model.dto.request;

import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @date 05/03/2019
 */
@Data
@ToString
public class FcDeviceRequestDto {
    private Long userId;

    private String deviceSerialNo;

    private String deviceDescription;

    private String deviceNickname;

    private Boolean periodEnable = false;
}
