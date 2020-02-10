package com.zzx.graduation;

import com.zzx.graduation.entity.Staff;
import com.zzx.graduation.entity.User;
import com.zzx.graduation.repository.StaffRepository;
import com.zzx.graduation.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class GraduationApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;//k-v操作字符串的，简化操作
    @Autowired
    RedisTemplate redisTemplate;//k-v 操作都是对象
    @Autowired
    RedisTemplate<Object, Staff> redisTemplate01;
    //自定义json序列化器,也可以吧对象转成json格式存入redis
    @Autowired
    RabbitTemplate rabbitTemplate;


    @Autowired
    UserRepository userRepository;
    @Autowired
    StaffRepository staffRepository;




    /**
     * redis常见五大数据类型
     * String，list，set，还是，zset（有序集合）
     * stringRedisTemplate.opsForValue()字符串
     * stringRedisTemplate.opsForList()列表
     * stringRedisTemplate.opsForSet()集合
     * stringRedisTemplate.opsForHash() 散列
     * stringRedisTemplate.opsForZSet() 有序集合
     */
    @Test
    public void tet1() {
        stringRedisTemplate.opsForValue().append("msg", "hello");

        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
        //自定义json序列化器
        Staff staff = staffRepository.findById(1).get();
        redisTemplate01.opsForValue().set("staff", staff);
    }

    /**
     * 单播，点对点
     */
    @Test
    public void test01() {
        //Message需要自己构造，定义消息体内容和消息头

        //object默认当成消息体，只需要传入要发送的独享，自动序列化发送给rabbit
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct", "zzx.news", map);
    }

    @Test
    public void receive01() {
        Object o = rabbitTemplate.receiveAndConvert("zzx.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void sendBroadCast() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", "12345");
    }
}
