package com.ethan.common.controller;

import com.ethan.common.model.FcDevice;
import com.ethan.common.model.dto.request.FcDeviceRequestDto;
import com.ethan.common.model.dto.response.SimpleResponse;
import com.ethan.common.service.FcDeviceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @version 1.0
 * @date 07/03/2019
 */
@Api(value = "DeviceController", description = "Device Control Module", tags = "Device")
public class DeviceController extends BasicController {
    @Autowired
    private FcDeviceService deviceService;

    @GetMapping(value = "/devices")
    public ResponseEntity<List> getDeviceList() {
        Long userid = 1L;
        List<FcDevice> result = deviceService.findByUserId(userid);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/device/{deviceId}")
    public ResponseEntity<FcDevice> getDevice(@PathVariable("deviceId") Long deviceId) {
        FcDevice result = deviceService.findByDeviceId(deviceId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/device")
    public ResponseEntity<SimpleResponse> createDevice(@RequestBody FcDeviceRequestDto dto, Device device) {
        Long userid = 1L;
        int result = deviceService.insert(dto, userid);
        return ResponseEntity.ok(new SimpleResponse(true, "success"));
    }

    @PutMapping(value = "/device/{deviceId}")
    public ResponseEntity<SimpleResponse> updateDevice(@PathVariable("deviceId") Long deviceId, @RequestBody FcDevice entity, Device device) {
        Long userid = 1L;
        int result = deviceService.update(entity, deviceId);
        return ResponseEntity.ok(new SimpleResponse(true, "success"));
    }

    @DeleteMapping(value = "/device/{deviceId}")
    public ResponseEntity<SimpleResponse> deleteDevice(@PathVariable("deviceId") Long deviceId, Device device) {
        Long userid = 1L;
        int result = deviceService.delete(deviceId);
        return ResponseEntity.ok(new SimpleResponse(true, "success"));
    }
}
