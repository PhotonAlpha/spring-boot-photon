/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.client.controller;

import com.client.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-24 17:04
 **/
// @Controller
@RestController("api")
public class IndexController {
    /**
     * @return index page
     */
    /*@RequestMapping({"/welcome", "/main/**", ""})
    public String index() {
        return "forward:/index.html";
    }*/

    @Autowired
    private ConfigProperties configProperties;

    @GetMapping(value = "/params")
    public String index() {
        configProperties.getGender();
        configProperties.setAge(20);
        return  configProperties.getName();
    }
}
