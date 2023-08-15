package com.example.a_train.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.a_train.entity.Main;
import com.example.a_train.mapper.MainMapper;
import com.example.a_train.service.MainService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MainImp extends ServiceImpl<MainMapper, Main> implements MainService {
    @Resource
    private MainMapper mainMapper;

    @Override
    public boolean save(Main main){return mainMapper.insert(main)>0;}

    @Override
    public boolean delete(Integer id){return mainMapper.deleteById(id)>0;}

    @Override
    public IPage<Main> getPage(int currentpage,int pagesize){
    IPage page=new Page(currentpage,pagesize);
    mainMapper.selectPage(page,null);
    return page;
    }
}
