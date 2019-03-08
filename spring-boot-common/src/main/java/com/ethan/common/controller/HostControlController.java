package com.ethan.common.controller;

import com.ethan.common.model.FcHostControl;
import com.ethan.common.service.FcHostControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @version 1.0
 * @date 07/03/2019
 */
@Api(value = "HostControlController", description = "Host Control Module", tags = "HostConterol")
public class HostControlController extends BasicController {

    @Autowired
    private FcHostControlService hostControlService;

    @ApiOperation(value = "查询白名单列表")
    @GetMapping("hostcs/{deviceId}")
    public ResponseEntity getHostList(@PathVariable("deviceId") Long deviceId) {
        List<FcHostControl> result = hostControlService.getWhiteListHost(deviceId);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "查询APP列表")
    @GetMapping("hostcapp/{deviceId}")
    public ResponseEntity getHostAppList(@PathVariable("deviceId") Long deviceId) {
        List<FcHostControl> result = hostControlService.getWhiteListHost(deviceId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("hostc/{deviceId}")
    public ResponseEntity createHostControl(@PathVariable("deviceId") Long deviceId) {
        List<FcHostControl> result = hostControlService.getWhiteListHost(deviceId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("hostc/{deviceId}")
    public ResponseEntity updateHostControl(@PathVariable("deviceId") Long deviceId) {
        List<FcHostControl> result = hostControlService.getWhiteListHost(deviceId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("hostc/{deviceId}")
    public ResponseEntity deleteHostControl(@PathVariable("deviceId") Long deviceId) {
        List<FcHostControl> result = hostControlService.getWhiteListHost(deviceId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("hostcapp/{deviceId}")
    public ResponseEntity addHostToApp(@PathVariable("deviceId") Long deviceId) {
        List<FcHostControl> result = hostControlService.getWhiteListHost(deviceId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("hostcapp/{deviceId}")
    public ResponseEntity removeHostToApp(@PathVariable("deviceId") Long deviceId) {
        List<FcHostControl> result = hostControlService.getWhiteListHost(deviceId);
        return ResponseEntity.ok(result);
    }
}
