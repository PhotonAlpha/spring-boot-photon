/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-14 14:12
 **/

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"com.ethan.common.dao"})
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}




