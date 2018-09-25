/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-24 17:04
 **/
@Controller
public class IndexController {
    /**
     * @return index page
     */
    @RequestMapping({"/welcome", "/main/**", ""})
    public String index() {
        return "forward:/index.html";
    }
}
