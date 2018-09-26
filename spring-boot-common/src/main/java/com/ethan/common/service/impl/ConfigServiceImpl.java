/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.service.impl;

import com.ethan.common.service.ConfigService;
import com.ethan.core.constant.ServiceConstant;
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
@CacheConfig(cacheNames = {ServiceConstant.CACHE_DIRECTORY})
public class ConfigServiceImpl implements ConfigService {
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
}
