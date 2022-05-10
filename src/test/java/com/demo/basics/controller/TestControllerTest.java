package com.demo.basics.controller;

import com.demo.basics.BaseTest;
import com.demo.basics.BasicsApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author zph
 * @version 1.0
 * @date 2022/5/10 9:46
 */
@SpringBootTest(classes = {BasicsApplication.class, MockServletContext.class})
@WebAppConfiguration
class TestControllerTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    public MockMvc mockMvc;


    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test() throws Exception{
        String url = "/test";
        String json = "";
        testPostRequestJson(url,json,mockMvc);
    }

}