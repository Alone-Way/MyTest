package com.along.studymongo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @auther: Along
 * @description: mongo 业务测试类
 * @Date: created in 2019/4/26 16:12
 */
@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(TestService.class);

    /**
     * mongoTemplate 已经帮我们封装了许多东西，我已经不想再写Dao层了（感觉写Dao 层就是多余了），直接让其当Dao 层
     */
    @Autowired
    private MongoTemplate mongoTemplate;
}
