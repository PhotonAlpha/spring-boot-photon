package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcUserAudit;

public interface FcUserAuditMapper {

    int insert(@Param("pojo") FcUserAudit pojo);

    int insertList(@Param("pojos") List< FcUserAudit> pojo);

    List<FcUserAudit> select(@Param("pojo") FcUserAudit pojo);

    int update(@Param("pojo") FcUserAudit pojo);

}
