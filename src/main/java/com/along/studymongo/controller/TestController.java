package com.along.studymongo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: Along
 * @description: 类说明
 * @Date: created in 2019/4/26 16:15
 */
@RestController
@RequestMapping("/Along")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Map<String,Object> test(){
        return new HashMap<String,Object>(){{
            put("a",1);
            put("b","你好");
        }};
    }

    @RequestMapping(value = "")
    public String str(){
        return "Hello world!";
    }
}
