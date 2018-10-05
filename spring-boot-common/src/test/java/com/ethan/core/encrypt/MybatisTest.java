/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package encrypt;

import com.aisp.CommonApplication;
import com.aisp.common.model.Codes;
import com.aisp.common.service.ConfigService;
import com.aisp.core.model.Pagination;
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
        Codes dic1 = new Codes().setKey("sg").setValue("singapore").setType("country");
        Codes dic2 = new Codes().setKey("id").setValue("india").setType("country");
        Codes dic3 = new Codes().setKey("hk").setValue("hongkong").setType("country");

        configService.insertDic(dic1);
        configService.insertDic(dic2);
        configService.insertDic(dic3);

        Codes dicold = configService.getOne(1L);
        System.out.println(dicold);

        Pagination page = new Pagination();
        page = configService.findKeyByPage(page);
        System.out.println("page>>>>>>>>>>");
        System.out.println(page);

        List<Codes> result = configService.getAll();
        System.out.println(result);

        dicold.setKey("cn");
        configService.updateDic(dicold);

        configService.deleteDic(1L);

        result = configService.getAll();
        System.out.println(result);



    }
}
