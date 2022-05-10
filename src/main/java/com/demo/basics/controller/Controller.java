package com.demo.basics.controller;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller {

    Logger logger = LoggerFactory.getLogger(Controller.class);



    @RequestMapping(value="/test")
    public Object query(){
        logger.info("测试");
        Gson gson = new Gson();
        String json = "测试";
        return json;
    }



}
