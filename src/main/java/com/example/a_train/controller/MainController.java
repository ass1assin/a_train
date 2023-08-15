package com.example.a_train.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.a_train.controller.utils.Result;
import com.example.a_train.entity.Main;
import com.example.a_train.service.MainService;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Slf4j
@RestController
@RequestMapping("/main")
public class MainController{

    @Autowired
    private MainService mainService;

    @GetMapping
    public Result getAll(){
        return new Result(true,mainService.list());}

//@GetMapping 适用于获取数据和查询操作，(客户端可以通过 URL 参数传递数据[delete]那个也行，非常方便。)
//@PostMapping 适用于提交数据、数据修改和创建操作。(可以通过请求体传递复杂数据，如 JSON、XML 等。
//提供更好的安全性，适合传递敏感信息。)
    @PostMapping
//@RequestBody Main main 用于从请求体中获取传递过来的 JSON 数据，并将它映射到 Main 类型的对象
    public Result save(@RequestBody Main main){
        return new Result(true,mainService.save(main));
    }
//
    @DeleteMapping("/{id}")
//@PathVariable 注解。它表示从 URL 路径中提取出的名为 id 的变量值。
    public Result delete(@PathVariable Integer id){
        return new Result(true,mainService.delete(id));
    }

    @GetMapping("{currentpage}/{pagesize}")
    public Result getPage(@PathVariable int currentpage,@PathVariable int pagesize){
        IPage<Main> page= mainService.getPage(currentpage,pagesize);
        //如果当前页码值大于总页码数，那么重新执行查询操作，使用最大页码值作为当前页码值。
        if (currentpage > page.getPages()){
//将当前页码设置为总页数，以防止超出范围的情况。((int)page.getPages()总页数)
            page = mainService.getPage((int)page.getPages(),pagesize);
        }

        return new Result(true,mainService.getPage(currentpage,pagesize));
    }

}