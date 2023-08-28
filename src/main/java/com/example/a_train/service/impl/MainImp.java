package com.example.a_train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.a_train.controller.utils.Result;
import com.example.a_train.entity.Main;
import com.example.a_train.mapper.MainMapper;
import com.example.a_train.service.MainService;
import com.example.a_train.utils.CacheClient;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.a_train.utils.RedisConstants.*;

@Service
public class MainImp extends ServiceImpl<MainMapper, Main> implements MainService {
    @Resource
    private MainMapper mainMapper;

    @Resource
    private CacheClient cacheClient;

    @Override
    public boolean save(Main main){return mainMapper.insert(main)>0;}


    @Override
    public boolean delete(Integer id){return mainMapper.deleteById(id)>0;}

    @Override
    public Result getByall(String name, Integer number, String gender){

//        Main main=cacheClient.findCache(CACHE_SHOP_KEY,name,number,gender,Main.class,this::getById);

            QueryWrapper<Main> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", name);
            queryWrapper.eq("number",number);
            queryWrapper.eq("gender", gender);
            return Result.rs(mainMapper.selectList(queryWrapper));

    }

    @Override
    public IPage<Main> getPage(int currentpage,int pagesize){
    IPage page=new Page(currentpage,pagesize);
    mainMapper.selectPage(page,null);
    return page;
    }
}
