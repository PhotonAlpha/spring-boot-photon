/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.config;

import com.ethan.core.constant.ServiceConstant;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-26 13:25
 **/
@Configuration
public class CaffeineConfig {
    private final Logger logger = LoggerFactory.getLogger(CaffeineConfig.class);
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager(ServiceConstant.CACHE_DIRECTORY, ServiceConstant.CACHE_INSTRUMENT, ServiceConstant.CACHE_THREAD);
        manager.setAllowNullValues(false);
        manager.setCaffeine(caffeineCacheBuilder());
        return manager;
    }

    private Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(200)
                .expireAfterWrite(100, TimeUnit.MINUTES)
                // .expireAfterAccess(1, TimeUnit.MINUTES)
                // .weakKeys()
                .weakValues()
                .removalListener(new CustomRemovalListener())
                .recordStats();
    }

    class CustomRemovalListener implements RemovalListener<Object, Object> {
        @Override
        public void onRemoval(Object key, Object value, RemovalCause cause) {
            logger.info(">>>>>>>>removal listerner called with key {}, cause {}, evicted {}", key, cause.toString(), cause.wasEvicted());
        }
    }
}
