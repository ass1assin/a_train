package com.example.a_train.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class CacheClient {

    private final StringRedisTemplate stringRedisTemplate;

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

    public void setWithLogicalExpire(String key, Object value) {
        // 设置逻辑过期
        RedisData redisData = new RedisData();
//        创建一个RedisData对象，将value和逻辑过期时间封装在其中。
        redisData.setData(value);
//        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        // 写入Redis
//        将该对象转换为JSON字符串，并将其存储到Redis中。
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

//从缓存中查询数据
//    <R,ID> R这使得该方法可以根据需要处理不同类型的数据
//    type 参数将数据映射为指定的Java类型 R。
//    dbFallback：一个 Function<ID, R> 类型的参数，表示当缓存中没有数据时，从数据库中获取数据的回退函数。
    public <R,ID> R findCache(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback){
//        传递的标识查找缓存
        String key=keyPrefix+id;
       String json=stringRedisTemplate.opsForValue().get(key);

      if(StrUtil.isNotBlank(json)){
          return JSONUtil.toBean(json,type);
      }
//      判断命中的是否是空值
        if (json != null) {
            // 返回一个错误信息
            return null;
        }

//      缓存内不存在，则在数据库查
      R r=dbFallback.apply(id);
//      数据库也不存在
      if (r==null)
      {
          stringRedisTemplate.opsForValue().set(key,"");
          return null;
      }
//      数据库存在则写入redis
      this.set(key,r);
      return r;
    }
}
