package test.com.springboot.demo.controller;

import com.springboot.demo.DemoApplication;
import com.springboot.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName: ApplicationTests
 * @Auther: zhangyingqi
 * @Date: 2018/8/29 10:46
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ApplicationTests {
    /**
     * 模拟mvc测试对象
     */
    private MockMvc mockMvc;

    /**
     * web项目上下文
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 商品业务数据接口
     */
    @Autowired
    private UserService userService;

    /**
     * 所有测试方法执行之前执行该方法
     */
    @Before
    public void before() {
        //获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 测试访问/index地址
     * @throws Exception
     */
    @Test
    public void testPage() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(// 1
                        MockMvcRequestBuilders.get("/user/list") // 2
                                //.param("name","getList")        // 3
                )
                .andReturn();// 4

        int status = mvcResult.getResponse().getStatus(); // 5
        String responseString = mvcResult.getResponse().getContentAsString(); // 6

        Assert.assertEquals("请求错误", 200, status); // 7
        Assert.assertEquals("返回结果不一致", "/demoPage/listPage", responseString); // 8
    }

    @Test
    public void testJson() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(// 1
                        MockMvcRequestBuilders.post("/user/add") // 2
                                .param("name","小王") // 3
                                .param("age","26")
                )
                .andReturn();// 4

        int status = mvcResult.getResponse().getStatus(); // 5
        String responseString = mvcResult.getResponse().getContentAsString(); // 6

        Assert.assertEquals("请求错误", 200, status); // 7
        Assert.assertEquals("返回结果不一致", "{\"status\":\"1\",\"data\":\"这是一条测试数据\",\"msg\":\"保存成功\"}", responseString); // 8
    }


}
