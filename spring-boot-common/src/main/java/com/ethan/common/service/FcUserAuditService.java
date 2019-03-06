package com.ethan.common.service;

import com.ethan.common.dao.FcUserAuditMapper;
import com.ethan.common.model.FcUserAudit;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class FcUserAuditService {

    @Resource
    private FcUserAuditMapper fcUserAuditDao;

    public int insert(FcUserAudit pojo){
        return fcUserAuditDao.insert(pojo);
    }

    public int insertList(List< FcUserAudit> pojos){
        return fcUserAuditDao.insertList(pojos);
    }

    public List<FcUserAudit> select(FcUserAudit pojo){
        return fcUserAuditDao.select(pojo);
    }

    public int update(FcUserAudit pojo){
        return fcUserAuditDao.update(pojo);
    }

}
