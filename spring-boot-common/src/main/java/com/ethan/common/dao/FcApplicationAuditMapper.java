package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcApplicationAudit;

public interface FcApplicationAuditMapper {

    int insert(@Param("pojo") FcApplicationAudit pojo);

    int insertList(@Param("pojos") List< FcApplicationAudit> pojo);

    List<FcApplicationAudit> select(@Param("pojo") FcApplicationAudit pojo);

    int update(@Param("pojo") FcApplicationAudit pojo);

}
