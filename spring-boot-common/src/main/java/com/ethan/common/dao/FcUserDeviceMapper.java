package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcUserDevice;

public interface FcUserDeviceMapper {

    int insert(@Param("pojo") FcUserDevice pojo);

    int insertList(@Param("pojos") List< FcUserDevice> pojo);

    List<FcUserDevice> select(@Param("pojo") FcUserDevice pojo);

    int update(@Param("pojo") FcUserDevice pojo);

}
