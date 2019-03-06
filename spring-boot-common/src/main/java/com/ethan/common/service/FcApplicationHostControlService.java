package com.ethan.common.service;

import com.ethan.common.dao.FcApplicationHostControlMapper;
import com.ethan.common.model.FcApplicationHostControl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class FcApplicationHostControlService {

    @Resource
    private FcApplicationHostControlMapper fcApplicationHostControlDao;

    public int insert(FcApplicationHostControl pojo){
        return fcApplicationHostControlDao.insert(pojo);
    }

    public int insertList(List< FcApplicationHostControl> pojos){
        return fcApplicationHostControlDao.insertList(pojos);
    }

    public List<FcApplicationHostControl> select(FcApplicationHostControl pojo){
        return fcApplicationHostControlDao.select(pojo);
    }

    public int update(FcApplicationHostControl pojo){
        return fcApplicationHostControlDao.update(pojo);
    }

}
