package com.along.studymongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import sun.util.resources.cldr.id.CurrencyNames_id;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther: Along
 * @description: mongo 实体类 测试类
 * @Date: created in 2019/4/26 15:57
 */
@Data
public class Test {

    @Id
    private String _id;// 这个_id 是mongo默认id，必须是“_id”，查询数据时，既可以根据_id 查询也可以根据 testId 查询

    private Long testId; // 这个是我们实体类id

//    *************************************
//        关于我为什么设置两个id，是因为，mongo 默认id 是经过乱七八糟的算法生成的16进制的字符串，
//    我们项目中，假设数据库起初设计id 是long 类型或者int 类型，那么是没办法互相关联使用的，所以为了解决这个问题，
//    我又新增了一个标识id，_id 是mongo插入数据，默认添加的数据，我们用不用，都可以，只要有唯一标识就好了。
//    哦，mongo插入数据除了会添加 _id 标识外，还会添加 “_class” 属性，可自行在mongo客户端查询数据去查看
//    *************************************
    private String name;

    private int age;

    // 日期注意项：我们传入日期数据（正常），插入mongo 会减少8小时
    // （想测试mongo时间，可在mongo 客户端输入ISODate()获取，
    // mongo默认是ISODate，也可输入Date()查询，细心会发现，后面加上了GMT+8 标志）
    private Date createTime;

    // 用于测试mongo 中数组 类型数据，后期用于 测试包含匹配数据 的 mongo数据
    private List<Map<String,Object>> data;
}
