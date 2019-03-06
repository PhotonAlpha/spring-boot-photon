package com.ethan.common.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.ethan.common.model.FcApplication;
import com.ethan.common.dao.FcApplicationMapper;

@Service
public class FcApplicationService {

    @Resource
    private FcApplicationMapper fcApplicationDao;

    public int insert(FcApplication pojo){
        return fcApplicationDao.insert(pojo);
    }

    public int insertList(List< FcApplication> pojos){
        return fcApplicationDao.insertList(pojos);
    }

    public List<FcApplication> select(FcApplication pojo){
        return fcApplicationDao.select(pojo);
    }

    public int update(FcApplication pojo){
        return fcApplicationDao.update(pojo);
    }

}
