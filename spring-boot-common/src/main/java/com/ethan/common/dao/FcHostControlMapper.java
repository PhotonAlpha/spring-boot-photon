package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcHostControl;

public interface FcHostControlMapper {

    int insert(@Param("pojo") FcHostControl pojo);

    int insertList(@Param("pojos") List< FcHostControl> pojo);

    List<FcHostControl> select(@Param("pojo") FcHostControl pojo);

    int update(@Param("pojo") FcHostControl pojo);

}
