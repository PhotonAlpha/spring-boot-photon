package com.ethan.common.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ethan.common.model.FcApplicationHostControl;

public interface FcApplicationHostControlMapper {

    int insert(@Param("pojo") FcApplicationHostControl pojo);

    int insertList(@Param("pojos") List< FcApplicationHostControl> pojo);

    List<FcApplicationHostControl> getByAppId(@Param("appId") Long appId);

    int update(@Param("pojo") FcApplicationHostControl pojo);

    int delete(@Param("id") Long id);

    int deleteList(@Param("ids") List<Long> ids);
}
