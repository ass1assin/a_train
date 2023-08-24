package com.example.a_train.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.a_train.entity.Main;

import java.util.List;

public interface MainService extends IService<Main> {
    boolean save(Main main);

//    boolean update(Main main);
    boolean delete(Integer id);

//    boolean getById(Integer id);
    List<Main> getByall(String name, Integer number, String gender);
    IPage<Main> getPage(int currentpage,int pagesize);
}
