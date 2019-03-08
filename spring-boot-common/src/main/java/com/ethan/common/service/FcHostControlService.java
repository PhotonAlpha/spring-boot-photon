package com.ethan.common.service;

import com.ethan.common.dao.FcApplicationHostControlMapper;
import com.ethan.common.model.FcApplicationHostControl;
import com.ethan.common.model.FcHostControl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.ethan.common.dao.FcHostControlMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FcHostControlService {

    @Resource
    private FcHostControlMapper fcHostControlMapper;
    @Resource
    private FcApplicationHostControlMapper applicationHostControlMapper;

    public int insert(Long deviceId, FcHostControl pojo){
        return fcHostControlMapper.insert(pojo);
    }

    public int update(Long hostId, FcHostControl pojo){
        return fcHostControlMapper.update(pojo);
    }

    public int delete(Long hostId){
        return fcHostControlMapper.delete(hostId);
    }

    public List<FcHostControl> getWhiteListHost(Long deviceId){
        return fcHostControlMapper.getHostListByDeviceId(deviceId);
    }

    @Transactional(rollbackFor = Exception.class)
    public int banApplicationCommand(Long appId, Long deviceId){
        List<FcHostControl> hostControl = fcHostControlMapper.getHostListByDeviceId(deviceId);
        if (hostControl != null && !hostControl.isEmpty()) {
            List<FcApplicationHostControl> blackList = hostControl.stream().map(host -> FcApplicationHostControl.builder()
                    .appId(appId).hostControlId(host.getId())
                    .build()
            ).collect(Collectors.toList());
            return applicationHostControlMapper.insertList(blackList);

        } else {
            return 0;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public int openApplicationCommand(Long appId, Long deviceId){
        List<FcApplicationHostControl> hosts = applicationHostControlMapper.getByAppId(appId);
        if (hosts != null && !hosts.isEmpty()) {
            List<Long> hostIds = hosts.stream().map(host -> host.getHostControlId()).collect(Collectors.toList());
            return applicationHostControlMapper.deleteList(hostIds);
        } else {
            return 0;
        }
    }

}
