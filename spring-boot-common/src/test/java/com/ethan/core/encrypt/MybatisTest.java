/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.core.encrypt;

import com.ethan.CommonApplication;
import com.ethan.common.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-30 01:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class MybatisTest {
    @Autowired
    private ConfigService configService;

    @Test
    public void testDB() {



    }
}
