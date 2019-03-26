/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.service.impl;

import com.ethan.common.dao.AppDictionaryMapper;
import com.ethan.common.model.AppDictionary;
import com.ethan.common.service.ConfigService;
import com.ethan.core.constant.ServiceConstant;
import com.ethan.core.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-26 14:12
 **/
@Service
@CacheConfig(cacheNames = {ServiceConstant.DB_SERVICE})
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private AppDictionaryMapper dictionaryMapper;

    @Cacheable
    @Override
    public String play(Long appId, String type, String operator) {
        System.out.println("########################Executing: " + this.getClass().getSimpleName() + ".play(id:"+appId+";type:"+type+";operator:"+operator+");");
        return "Playing(id:"+appId+";type:"+type+";operator:"+operator+")";
    }

    @Cacheable(condition = "#content.equals('trombone')")
    @Override
    public List<String> put(String content) {
        System.out.printf("########################Executing: %s .play(%s)", this.getClass().getSimpleName(), content);
        System.out.println();
        return Arrays.asList("playing " + content + "!");
    }

    @Override
    public AppDictionary getOne(Long id) {
        return dictionaryMapper.getOne(id);
    }

    @Override
    public List<AppDictionary> getAll() {
        return dictionaryMapper.getAll();
    }

    @Override
    public boolean insertDic(AppDictionary entity) {
        int count = dictionaryMapper.insert(entity);
        System.out.println("insertDic"+count);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDic(AppDictionary entity) {
        int count = dictionaryMapper.update(entity);
        System.out.println("updateDic"+count);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDic(Long id) {
        int count = dictionaryMapper.delete(id);
        System.out.println("deleteDic"+count);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Pagination findKeyByPage(Pagination pagination) {
        pagination = new Pagination();
        pagination.setPage(true);
        pagination.setPageSize(2);
        pagination.setCurrPage(2);
        List<AppDictionary> results = dictionaryMapper.findKeyByPage(pagination);
        pagination.setResults(results);
        return pagination;
    }
}
