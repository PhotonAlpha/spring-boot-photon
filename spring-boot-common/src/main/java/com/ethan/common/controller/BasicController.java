/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-26 09:58
 **/
@RestController
@RequestMapping("v1")
public abstract class BasicController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
}
