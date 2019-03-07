package com.ethan.common.controller;

import com.ethan.common.model.FcDevice;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @version 1.0
 * @date 07/03/2019
 */
@Api(value = "Application Manager Controller", description = "Application Module", tags = "Application")
public class AppManagerController extends BasicController {
    @GetMapping(value = "/devices")
    public ResponseEntity<?> getDeviceList() {
        Long userid = 1L;
        // List<FcDevice> result = deviceService.findByUserId(userid);
        return ResponseEntity.ok("");
    }
}
