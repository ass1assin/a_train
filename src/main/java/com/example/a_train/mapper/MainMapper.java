package com.example.a_train.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.a_train.entity.Main;
import org.apache.ibatis.annotations.Mapper;

//BaseMapper 是 MyBatis-Plus 框架提供的基础映射器接口。
//@Mapper 注解标识这个接口为 MyBatis 的映射器，MyBatis 框架会根据这个接口定义的方法来生成相应的 SQL 语句，从而实现与数据库的交互。
@Mapper
public interface MainMapper extends BaseMapper<Main> {
}
