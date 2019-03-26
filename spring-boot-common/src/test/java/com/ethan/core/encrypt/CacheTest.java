/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.encrypt;

import com.ethan.CommonApplication;
import com.ethan.common.service.ConfigService;
import com.ethan.core.constant.ServiceConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-26 14:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class CacheTest {
    @Autowired
    private ConfigService svc;
    @Autowired
    private CacheManager cacheManager;
    @Test
    public void testCache() {
        svc.play(1L, "do", "show");
        svc.play(2L, "do", "show");
        System.out.println("----1-----");
        svc.play(1L, "do", "show");
        svc.play(2L, "do", "show");
        System.out.println("----2-----");
        svc.play(1L, "do", "show");
        svc.play(2L, "do", "show");
        System.out.println("----3-----");
        svc.play(1L, "do", "show");
        svc.play(2L, "do", "show");
        System.out.println("----11-----");
        svc.put("trombone");
        svc.put("trombone1");
        System.out.println("----22-----");
        svc.put("trombone");
        svc.put("trombone1");
        System.out.println("----33-----");
        svc.put("trombone");
        svc.put("trombone1");
    }
    // @Test
    public void dummyExecuteScript() {
        Long id = 2L;
        String res = id.toString();
        System.out.println(res);
        //if editable execute the shell command else get message form cache
        Cache cache = cacheManager.getCache(ServiceConstant.DB_SERVICE);
        cache.put(res, "123");
        com.github.benmanes.caffeine.cache.Cache nativeCoffeeCache = (com.github.benmanes.caffeine.cache.Cache) cache.getNativeCache();
        System.out.println(nativeCoffeeCache.asMap().toString());
        System.out.println(nativeCoffeeCache.stats());
        System.out.println(nativeCoffeeCache.getIfPresent("123"));
        String infoDto = cache.get(res, String.class);
        System.out.println(infoDto);
    }
}
