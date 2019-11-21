package com.railwayTicketManager.controller;

import com.railwayTicketManager.config.RedisClient;
import com.railwayTicketManager.config.UserDefinedException;
import com.railwayTicketManager.dao.po.UserPO;
import com.railwayTicketManager.services.UserSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: meizx
 * @ClassName: TestController
 * @Date: 2019/11/20 9:16
 * @E-mail： meizxbj@gmail.com
 * @Description: TestController
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RedisClient redisClient;
    /*@Autowired
    private MongoDBClient<TestReqBO> mongoDBClient;*/
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    UserSercive userSercive;

    @RequestMapping(value = "/str", method = RequestMethod.GET)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public String test(){
        String s = null;
        try {
            redisClient.set("a", "一言听尽肝肠断");
            redisClient.set("y", "天涯何处觅知音");
            s = redisClient.get("y");
        } catch (UserDefinedException e) {
            e.printStackTrace();
        }
        return s;
    }



    /*@RequestMapping(value = "/objStr", method = RequestMethod.GET)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public int test1(){
        TestReqBO test = new TestReqBO();
        test.setId(56789);
        test.setName("meizx");
        mongoTemplate.save(test);
        Criteria criteria=where("name").in("meizx");
        List<TestReqBO> tyest=mongoTemplate.find(query(criteria),TestReqBO.class);
        return tyest.get(0).getId();
    }*/

   /* @RequestMapping(value = "/iint", method = RequestMethod.GET)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public int test2(){
        return userSercive.test();
    }*/
}
