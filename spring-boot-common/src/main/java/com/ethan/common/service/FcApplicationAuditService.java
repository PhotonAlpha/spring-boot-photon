package com.ethan.common.service;

import com.ethan.common.model.FcApplicationAudit;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.ethan.common.dao.FcApplicationAuditMapper;

@Service
public class FcApplicationAuditService {

    @Resource
    private FcApplicationAuditMapper fcApplicationAuditDao;

    public int insert(FcApplicationAudit pojo){
        return fcApplicationAuditDao.insert(pojo);
    }

    public int insertList(List< FcApplicationAudit> pojos){
        return fcApplicationAuditDao.insertList(pojos);
    }

    public List<FcApplicationAudit> select(FcApplicationAudit pojo){
        return fcApplicationAuditDao.select(pojo);
    }

    public int update(FcApplicationAudit pojo){
        return fcApplicationAuditDao.update(pojo);
    }

}
