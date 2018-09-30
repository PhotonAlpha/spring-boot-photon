/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.core.encrypt;

import com.ethan.CommonApplication;
import com.ethan.common.model.AppDictionary;
import com.ethan.common.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: aisp
 * @description: TODO
 * @author: qiang.caocq@ncs.com.sg
 * @creat_date: 2018-09-30 01:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class MybatisTest {
    @Autowired
    private ConfigService configService;

    @Test
    public void testDB() {
        AppDictionary dic1 = new AppDictionary().setKey("sg").setValue("singapore").setGroupName("country");
        AppDictionary dic2 = new AppDictionary().setKey("id").setValue("india").setGroupName("country");
        AppDictionary dic3 = new AppDictionary().setKey("hk").setValue("hongkong").setGroupName("country");

        configService.insertDic(dic1);
        configService.insertDic(dic2);
        configService.insertDic(dic3);

        AppDictionary dicold = configService.getOne(1L);
        System.out.println(dicold);

        List<AppDictionary> result = configService.getAll();
        System.out.println(result);

        dicold.setKey("cn");
        configService.updateDic(dicold);

        configService.deleteDic(1L);

        result = configService.getAll();
        System.out.println(result);



    }
}
