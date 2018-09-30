/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.common.dao;

import com.ethan.common.model.AppDictionary;
import com.ethan.core.model.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-29 23:21
 **/
public interface AppDictionaryMapper {
    List<AppDictionary> getAll();

    AppDictionary getOne(@Param("id") Long id);

    List<AppDictionary> findKeyByPage(@Param("page") Pagination pagination);

    int insert(AppDictionary entity);

    int update(AppDictionary entity);

    int delete(Long id);


}
