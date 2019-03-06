package com.ethan.common.service;

import com.ethan.common.model.FcUserDevice;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.ethan.common.dao.FcUserDeviceMapper;

@Service
public class FcUserDeviceService {

    @Resource
    private FcUserDeviceMapper fcUserDeviceDao;

    public int insert(FcUserDevice pojo){
        return fcUserDeviceDao.insert(pojo);
    }

    public int insertList(List< FcUserDevice> pojos){
        return fcUserDeviceDao.insertList(pojos);
    }

    public List<FcUserDevice> select(FcUserDevice pojo){
        return fcUserDeviceDao.select(pojo);
    }

    public int update(FcUserDevice pojo){
        return fcUserDeviceDao.update(pojo);
    }

}
