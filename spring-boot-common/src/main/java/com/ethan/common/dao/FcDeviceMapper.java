package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcDevice;

public interface FcDeviceMapper {

    int insert(@Param("pojo") FcDevice pojo);

    int insertList(@Param("pojos") List< FcDevice> pojo);

    List<FcDevice> select(@Param("pojo") FcDevice pojo);

    int update(@Param("pojo") FcDevice pojo);

}
