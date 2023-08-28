package com.example.a_train.controller.utils;


import lombok.Data;

@Data
public class Result {
//    很多操作可能会返回不同的结果，如成功、失败、异常等。使用 Result 类型可以将操作的结果包装起来，以一种统一的方式进行返回
    private Boolean flag;
    private Object data;

    int total;

//    两个构造函数提供了不同的灵活性，可以根据不同的业务需求选择适合的构造函数来创建 Result 对象。
    public Result(Boolean flag){
        this.flag = flag;
    }
    public Result(Boolean flag, Object data){
        this.flag = flag;
        this.data = data;
    }
    public static Result rs(Object data){
        return new Result(true,data);
    }
}
