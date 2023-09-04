package com.example.a_train.utils;

import cn.hutool.core.util.StrUtil;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.a_train.entity.Main;
import com.example.a_train.mapper.MainMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;

import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class CacheClient {

    @Resource
    private MainMapper mainMapper;
    private StringRedisTemplate stringRedisTemplate;

    public CacheClient(StringRedisTemplate stringRedisTemplate){
    this.stringRedisTemplate=stringRedisTemplate;
    }

//用于向缓存中设置数据
  //    key：表示要存储在Redis中的缓存键。value：表示要存储在Redis中的缓存值，这里使用了Object类型，通常会将要存储的数据转换成JSON字符串进行存储。
    public void set(String key,Object value){
  //    opsForValue()方法:该方法返回一个用于操作字符串类型数据的操作对象。(此对象可以执行诸如设置、获取、删除等操作)
  //    JSONUtil.toJsonStr(value):将value对象转换为JSON格式的字符串(之前是Java对象)
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(value));
    }

//向缓存中设置数据(与set区别：RedisData对象是一个自定义的数据结构，可以包含除了数据本身之外的其他信息)
    public void setWithLogicalExpire(String key, Object value) {
        RedisData redisData = new RedisData();
  //        创建一个RedisData对象，将value封装在其中。
        redisData.setData(value);
        // 写入Redis
  //        将该对象转换为JSON字符串，并将其存储到Redis中。
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

//从缓存中查询数据
  //    <R,ID> R这使得该方法可以根据需要处理不同类型的数据
  //    type 参数将数据映射为指定的Java类型 R。
  //    dbFallback：一个 Function<ID, R> 类型的参数，表示当缓存中没有数据时，从数据库中获取数据的回退函数。
    @SneakyThrows
    public List<Main> findCache(String keyPrefix, String name , Integer number, String gender){
//         传递的标识查找缓存
        String key=keyPrefix+name+number+gender;
        // 1.从redis查询商铺缓存
       String json=stringRedisTemplate.opsForValue().get(key);
//      返回了json:{"flag":true,"data":[{"id":3,"name":"阿斯顿","gender":"女","number":4},{"id":7,"name":"阿斯顿","gender":"女","number":4}],"total":0}
        System.out.println("json:"+json);

      if(StrUtil.isNotBlank(json)){
//       创建了 objectMapper，您就可以使用它来进行 JSON 数据的序列化和反序列化操作
          ObjectMapper objectMapper = new ObjectMapper();
////       将 JSON 数据解析成了一个 JSON 树结构
//          JsonNode jsonArray = objectMapper.readTree(json);
//          System.out.println("jsonArray:"+jsonArray);

////        将获取到的 JsonNode 对象转换为字符串形式
//          String jsonObject = String.valueOf(jsonArray.get(0));
//          System.out.println("jsonObject："+jsonObject);

//       readValue 方法，它允许您将 JSON 数据转换为 Java 对象, new TypeReference<List<Main>>() {}：这是 Jackson 的一种机制，用于处理泛型类型
          List<Main> mainList = objectMapper.readValue(json, new TypeReference<List<Main>>() {});
////        将获取到的jsonObject转换为对象
//          System.out.println("这里有"+JSONUtil.toBean(jsonObject,type));
          return mainList;
      }

        QueryWrapper<Main> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        queryWrapper.eq("number", number);
        queryWrapper.eq("gender", gender);

        List<Main> r= mainMapper.selectList(queryWrapper);
        System.out.println("存进去时候的："+r);

        this.set(key,r);
        return mainMapper.selectList(queryWrapper);


    }
}
