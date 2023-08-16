package com.example.a_train.config;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//分页拦截器（没有这个imp的selectPage返回所有数据，不是分页后的数据）
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
//        定义mapper拦截器
//        MybatisPlusInterceptor: 这是 MyBatis Plus 提供的拦截器，它可以用来拦截 MyBatis 的查询请求，从而实现各种功能，包括分页。
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        添加具体的拦截器
//（这导致的返回所有数据【PaginationInnerInterceptor()】）PaginationInnerInterceptor(): 这是 MyBatis Plus 内置的分页拦截器，它可以拦截查询请求，根据分页参数进行数据分页操作PaginationInnerInterceptor(): 这是 MyBatis Plus 内置的分页拦截器，它可以拦截查询请求，根据分页参数进行数据分页操作
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}

