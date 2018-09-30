/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.common.dao;

import com.ethan.common.model.AppDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: aisp
 * @description: TODO
 * @author: qiang.caocq@ncs.com.sg
 * @creat_date: 2018-09-29 23:21
 **/
public interface AppDictionaryMapper {
    List<AppDictionary> getAll();

    AppDictionary getOne(@Param("id") Long id);

    int insert(AppDictionary entity);

    int update(AppDictionary entity);

    int delete(Long id);


}
