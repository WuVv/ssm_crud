package com.panda.redis;

import javax.management.loading.PrivateClassLoader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 测试Redis
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-redis.xml"})
public class TestRedis {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void testPing(){
        redisTemplate.opsForValue().set("panda", "武威");
        System.out.println("value："+redisTemplate.opsForValue().get("panda"));        
    }
}