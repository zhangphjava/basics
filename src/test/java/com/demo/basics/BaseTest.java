package com.demo.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author zph
 * @version 1.0
 * @date 2022/4/22 9:25
 */
@SpringBootTest
public class BaseTest {

    Logger logger = LoggerFactory.getLogger(BaseTest.class);


    /**
     *
     * @param url
     * @param name
     * @param value
     * @throws Exception
     */
    public void  testGetRequest(String url,String name,String value,MockMvc mockMvc)throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param(name,value)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                //等同于Assert.assertEquals(200,status);
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //得到返回代码
        int status = mvcResult.getResponse().getStatus();
        //得到返回结果
        String content = mvcResult.getResponse().getContentAsString();
        logger.info("返回结果："+content);
    }

    /***
     *  测试get请求， 接受json
     * @param url
     * @param json
     * @throws Exception
     */
    public void  testGetRequestJson(String url,String json,MockMvc mockMvc)throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                //等同于Assert.assertEquals(200,status);
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //得到返回代码
        int status = mvcResult.getResponse().getStatus();
        //得到返回结果
        String content = mvcResult.getResponse().getContentAsString();
        logger.info("返回结果："+content);
    }


    /***
     * post请求方式
     * @param url
     * @param json
     * @throws Exception
     */
    public void  testPostRequestJson(String url,String json,MockMvc mockMvc)throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                //等同于Assert.assertEquals(200,status);
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //得到返回代码
        int status = mvcResult.getResponse().getStatus();
        //得到返回结果
        String content = mvcResult.getResponse().getContentAsString();
        logger.info("返回结果："+content);
    }

}
