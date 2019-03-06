package com.ethan.common.service;

import com.ethan.common.model.FcHostControl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.ethan.common.dao.FcHostControlMapper;

@Service
public class FcHostControlService {

    @Resource
    private FcHostControlMapper fcHostControlDao;

    public int insert(FcHostControl pojo){
        return fcHostControlDao.insert(pojo);
    }

    public int insertList(List< FcHostControl> pojos){
        return fcHostControlDao.insertList(pojos);
    }

    public List<FcHostControl> select(FcHostControl pojo){
        return fcHostControlDao.select(pojo);
    }

    public int update(FcHostControl pojo){
        return fcHostControlDao.update(pojo);
    }

}
