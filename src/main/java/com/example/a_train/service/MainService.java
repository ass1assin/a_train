package com.example.a_train.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.a_train.entity.Main;

public interface MainService extends IService<Main> {
    boolean save(Main main);

    boolean delete(Integer id);

    IPage<Main> getPage(int currentpage,int pagesize,Main main);
}
