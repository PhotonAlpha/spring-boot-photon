package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcHostControl;

public interface FcHostControlMapper {

    int insert(@Param("pojo") FcHostControl pojo);

    int insertList(@Param("pojos") List< FcHostControl> pojo);

    List<FcHostControl> getHostListByDeviceId(@Param("deviceId") Long deviceId);

    int update(@Param("pojo") FcHostControl pojo);

    int delete(@Param("id") Long id);

}
