package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcFeedback;

public interface FcFeedbackMapper {

    int insert(@Param("pojo") FcFeedback pojo);

    int insertList(@Param("pojos") List< FcFeedback> pojo);

    List<FcFeedback> select(@Param("pojo") FcFeedback pojo);

    int update(@Param("pojo") FcFeedback pojo);

}
