package com.ethan.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.ethan.common.model.FcApplication;
import com.ethan.common.dao.FcApplicationMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class FcApplicationService {

    @Resource
    private FcApplicationMapper fcApplicationDao;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional(rollbackFor = Exception.class)
    public int installApplication(Long deviceId, String appname){
        FcApplication callbackApp = mockRemoteInstallCall();

        FcApplication newApp = new FcApplication();
        newApp.setAppInstalled(true);
        newApp.setAppName(callbackApp.getAppName());
        newApp.setAppDescription(callbackApp.getAppDescription());
        newApp.setDeviceId(deviceId);
        newApp.setAppRecommend(true);

        return fcApplicationDao.insert(newApp);
    }

    public List<FcApplication> getAppsByDevice(Long deviceId){
        return fcApplicationDao.selectByDeviceId(deviceId);
    }

    @Transactional(rollbackFor = Exception.class)
    public int unInstallApplication(Long appId) throws Exception {
        Boolean success = mockRemoteUnInstallCall();
        if (success) {
            return fcApplicationDao.delete(appId);
        } else {
            throw new Exception("未知异常，卸载失败！");
        }
    }

    public FcApplication mockRemoteInstallCall() {
        //TODO call remote server
        FcApplication callbackApp = new FcApplication();
        callbackApp.setAppName("支付宝");
        callbackApp.setAppDescription("这是万能的淘宝网这是万能的淘宝网这是万能的淘宝网");
        return callbackApp;
    }
    public  Boolean mockRemoteUnInstallCall() {
        return true;
    }

}
