package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcApplication;

public interface FcApplicationMapper {

    int insert(@Param("pojo") FcApplication pojo);

    int insertList(@Param("pojos") List< FcApplication> pojo);

    List<FcApplication> selectByDeviceId(@Param("deviceId") Long deviceId);

    int update(@Param("pojo") FcApplication pojo);

    int delete(@Param("id") Long id);
}
