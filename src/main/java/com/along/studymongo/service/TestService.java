package com.along.studymongo.service;

import com.along.studymongo.entity.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @auther: Along
 * @description: mongo 业务测试类
 * @Date: created in 2019/4/26 16:12
 */
@Service
public class TestService {

    /**
     * mongoTemplate 已经帮我们封装了许多东西，我已经不想再写Dao层了（感觉写Dao 层就是多余了），直接让其当Dao 层
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * mongo 添加（测试save，insert方法）
     * @param test
     */
    public void saveTest(Test test){
        mongoTemplate.save(test);

        /**
         * insert 方法和 save 方法作用相同，但是mongo 推荐使用save
         */
//        mongoTemplate.insert(test);

    }

    /**
     * 根据id 删除数据（测试remove方法）
     * @param id
     * @return
     */
    public void deleteTest(Long id){
        /**
         * 注意点：因为Test类的testId属性是Long 类型的，在mongo中对应的是number 类型，
         * 虽然，is（Object obj）方法接收Object 类型，但是，如果你传String 类型是查询不到数据的，也就是会删除失败。
         * 即：mongo 不论删除还是查询，查询属性类型必须一一对应
         */
        mongoTemplate.remove(new Query(Criteria.where("testId").is(id)),Test.class);
    }

    /**
     * 根据id更新数据
     */
    public void updateById(Test test){
//        Update.update("name",test.getName()) Update.update() 方法用于更新某一个数据，原因：update 方法是new 了一个对象，然后set 方法

        // updateFirst 更新查询出来数据的第一条，如果查询出来2条数据，那么更新第一条数据
        mongoTemplate.updateFirst(new Query(Criteria.where("testId").is(test.getTestId())),
                new Update().set("name",test.getName()).set("age",test.getAge()).set("data",test.getData()),Test.class);

//        mongoTemplate.updateMulti();
        // updateMulti 方法用于更新 查询出来多条匹配的数据，比如，查询出来2条数据，2条数据均更新
    }


    /**
     * 统计相同年龄的数量有多少个(测试count方法)
     * @param age
     * @return
     */
    public long getCountByAge(int age){
        return mongoTemplate.count(new Query(Criteria.where("age").is(age)),Test.class);
    }

    /**
     * 根据id判断数据在mongo中是否存在（测试exists方法）
     */
    public boolean isExistsById(Long id){
        return mongoTemplate.exists(new Query(Criteria.where("testId").is(id)),Test.class);
    }

    /**
     * 分页查询
     */
    public List<Test> findByPage(Pageable pageable){
//        return mongoTemplate.find(new Query().with(pageable),Test.class);// 这种方法也可以满足

        // 分页排序查询（当然你也可以再添加其他条件）
        return mongoTemplate.find(new Query().with(Sort.by(Sort.Direction.DESC,"createTime")).with(pageable),Test.class);
    }

    /**
     * 测试查询 mongo 数组类型 数据匹配
     */
    public List<Test> findByData(Map<String,Object> data,Pageable pageable){
        return mongoTemplate.find(new Query(Criteria.where("data").is(data)).with(pageable),Test.class);
    }

    /**
     * 查询所有数据
     */
    public List<Test> findAll (){
        return mongoTemplate.findAll(Test.class);
    }

    /*
     * mongoTemplate 中还封装了许多其他的方法，我就不在此再全部演示了，常用场景，我已经做了演示，其余的，遇到，再说吧
     */
}
