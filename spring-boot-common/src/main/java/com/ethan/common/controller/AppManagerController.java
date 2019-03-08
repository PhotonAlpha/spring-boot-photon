package com.ethan.common.controller;

import com.ethan.common.model.FcApplication;
import com.ethan.common.model.dto.request.FcAppInstallRequestDto;
import com.ethan.common.model.dto.response.SimpleResponse;
import com.ethan.common.service.FcApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @version 1.0
 * @date 07/03/2019
 */
@Api(value = "Application Manager Controller", description = "Application Module", tags = "Application")
public class AppManagerController extends BasicController {
    @Autowired
    private FcApplicationService applicationService;

    @GetMapping(value = "/applications/{deviceId}")
    public ResponseEntity<List> getApplicationList(@PathVariable("deviceId") Long deviceId) {
        List<FcApplication> result = applicationService.getAppsByDevice(deviceId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/application")
    public ResponseEntity<SimpleResponse> installApplication(@RequestBody FcAppInstallRequestDto dto, Device device) {
        int result = applicationService.installApplication(dto.getDeviceId(), "app");
        return ResponseEntity.ok(new SimpleResponse(true, "success"));
    }

    @DeleteMapping(value = "/application/{appId}")
    public ResponseEntity<?> unInstallApplication(@PathVariable("appId") Long appId) throws Exception {

        int result = applicationService.unInstallApplication(appId);
        return ResponseEntity.ok(new SimpleResponse(true, "success"));
    }
}
