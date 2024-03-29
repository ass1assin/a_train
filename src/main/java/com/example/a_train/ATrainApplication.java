package com.example.a_train;

//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@Slf4j
//指定了扫描路径为 "com.mapper"，表示 MyBatis 将扫描该包下的 Mapper 接口，并将它们注册为 MyBatis 的映射器。
//@MapperScan("com.example.a_train.mapper")
////用于扫描并注册 Servlet、Filter、和 Listener 等 Web 组件。
//@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class ATrainApplication {

    public static void main(String[] args) {

        SpringApplication.run(ATrainApplication.class, args);
    }

}
