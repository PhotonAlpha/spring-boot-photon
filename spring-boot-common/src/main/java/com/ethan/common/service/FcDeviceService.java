package com.ethan.common.service;

import com.ethan.common.model.dto.request.FcDeviceRequestDto;
import com.ethan.core.providers.TimeProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.ethan.common.model.FcDevice;
import com.ethan.common.dao.FcDeviceMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FcDeviceService {

    @Resource
    private FcDeviceMapper fcDeviceMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insert(FcDeviceRequestDto dto, Long userId){
        FcDevice fcDevice = new FcDevice();
        BeanUtils.copyProperties(dto, fcDevice);
        fcDevice.setUserId(userId);
        fcDevice.setCreateDate(TimeProvider.now());
        fcDevice.setUpdateDate(TimeProvider.now());

        return fcDeviceMapper.insert(fcDevice);
    }

    /*public int insertList(List< FcDevice> pojos){
        return fcDeviceMapper.insertList(pojos);
    }*/

    public List<FcDevice> findByUserId(Long userId){
        return fcDeviceMapper.findByUserId(userId);
    }

    public FcDevice findByDeviceId(Long deviceId){
        return fcDeviceMapper.findByDeviceId(deviceId);
    }

    public int update(FcDevice pojo, Long deviceId){
        FcDevice originalDevice = fcDeviceMapper.findByDeviceId(deviceId);
        if (originalDevice == null) {
            throw new RuntimeException("未找到匹配的设备。");
        }
        pojo.setCreateDate(originalDevice.getCreateDate());
        pojo.setUpdateDate(TimeProvider.now());
        pojo.setId(originalDevice.getId());
        pojo.setEnable(originalDevice.getEnable());

        return fcDeviceMapper.update(pojo);
    }

    public int delete(Long deviceId) {
        FcDevice originalDevice = fcDeviceMapper.findByDeviceId(deviceId);
        if (originalDevice == null) {
            throw new RuntimeException("未找到匹配的设备。");
        }
        originalDevice.setEnable(false);
        originalDevice.setDisableDate(TimeProvider.now());
        return fcDeviceMapper.update(originalDevice);
    }

}
