/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.service;

import com.ethan.common.model.AppDictionary;
import com.ethan.core.model.Pagination;

import java.util.List;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-26 14:11
 **/
public interface ConfigService {
    String play(Long appId, String type, String operator);

    List<String> put(String content);

    AppDictionary getOne(Long id);
    List<AppDictionary> getAll();

    Pagination findKeyByPage(Pagination pagination);
    boolean insertDic(AppDictionary entity);
    boolean updateDic(AppDictionary entity);
    boolean deleteDic(Long id);
}
