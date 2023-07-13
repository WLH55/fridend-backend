package com.yupi.yupo.service;

import com.yupi.yupo.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author WLH
 * @verstion 1.0
 */
@SpringBootTest

public class RedisTest {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void test(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //增
        valueOperations.set("wlh","dog");
        valueOperations.set("cwl",1);
        valueOperations.set("陈文龙",1.1);
        User user = new User();
        user.setId(1L);
        user.setUsername("文千");
        valueOperations.set("天祥",user);
        System.out.println(valueOperations.get("天祥"));
        //查
//        Object yupi = valueOperations.get("wlh");
//        Assertions.assertTrue("dog".equals((String) yupi));
//        Object yupiInt = valueOperations.get("yupiInt");
//        Assertions.assertTrue(1 == (Integer) yupiInt);
//        Object yupiDouble = valueOperations.get("yupiDouble");
//        Assertions.assertTrue(1.1 == (Double) yupiDouble);
//        System.out.println(valueOperations.get("yupiUser"));
    }

}
