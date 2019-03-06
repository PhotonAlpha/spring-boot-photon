package com.ethan.common.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.ethan.common.model.FcDevice;
import com.ethan.common.dao.FcDeviceMapper;

@Service
public class FcDeviceService {

    @Resource
    private FcDeviceMapper fcDeviceDao;

    public int insert(FcDevice pojo){
        return fcDeviceDao.insert(pojo);
    }

    public int insertList(List< FcDevice> pojos){
        return fcDeviceDao.insertList(pojos);
    }

    public List<FcDevice> select(FcDevice pojo){
        return fcDeviceDao.select(pojo);
    }

    public int update(FcDevice pojo){
        return fcDeviceDao.update(pojo);
    }

}
