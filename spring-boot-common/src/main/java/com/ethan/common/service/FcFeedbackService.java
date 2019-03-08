package com.ethan.common.service;

import com.ethan.common.dao.FcFeedbackMapper;
import com.ethan.common.model.FcFeedback;
import com.ethan.common.model.dto.request.FcFeedbackRequestDto;
import com.ethan.core.providers.TimeProvider;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class FcFeedbackService {

    @Resource
    private FcFeedbackMapper fcFeedbackDao;

    public int insert(FcFeedbackRequestDto dto, Long userId){
        FcFeedback feedback = new FcFeedback();
        feedback.setUserId(userId);
        feedback.setFeedback(dto.getFeedback());
        feedback.setCreateDate(TimeProvider.now());

        return fcFeedbackDao.insert(feedback);
    }

    public int insertList(List< FcFeedback> pojos){
        return fcFeedbackDao.insertList(pojos);
    }

    public List<FcFeedback> select(FcFeedback pojo){
        return fcFeedbackDao.select(pojo);
    }

    public int update(FcFeedback pojo){
        return fcFeedbackDao.update(pojo);
    }

}
