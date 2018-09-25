/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-25 15:44
 **/
@RestController
public class TestController {
    @GetMapping(value = "/test/{name}")
    public ResponseEntity<?> getData(@PathVariable("name") String name) {
        return ResponseEntity.ok("hello" + name);
    }
}
