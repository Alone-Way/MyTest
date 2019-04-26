package com.along.studymongo.controller;

import com.along.studymongo.entity.Test;
import com.along.studymongo.msg.Response;
import com.along.studymongo.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @auther: Along
 * @description: 类说明
 * @Date: created in 2019/4/26 16:15
 */
@RestController
@RequestMapping("/Along")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Response addTest(@RequestBody Test test){
        return Response.success();
    }
}
