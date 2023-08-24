package com.example.a_train.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("students")
public class Main {

//为了确保序列化和反序列化的兼容性
//序列化（Serialization）是将对象的状态转换为可存储或传输的格式的过程，通常是将对象转换为字节流
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String gender;
    private Integer number;
}
