package com.example.a_train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.a_train.entity.Main;
import com.example.a_train.mapper.MainMapper;
import com.example.a_train.service.MainService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainImp extends ServiceImpl<MainMapper, Main> implements MainService {
    @Resource
    private MainMapper mainMapper;

    @Override
    public boolean save(Main main){return mainMapper.insert(main)>0;}

//    @Override
//    public boolean update(Main main){return mainMapper.updateById(main)>0;}

    @Override
    public boolean delete(Integer id){return mainMapper.deleteById(id)>0;}

//@Override 用于标识一个方法是重写了父类或接口中的方法
//    @Override
//    public boolean getById(Integer id){return mainMapper.get}

    @Override
    public List<Main> getByall(String name, Integer number, String gender){
        QueryWrapper<Main> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        queryWrapper.eq("number",number);
        queryWrapper.eq("gender", gender);
        return mainMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Main> getPage(int currentpage,int pagesize){
    IPage page=new Page(currentpage,pagesize);
    mainMapper.selectPage(page,null);
    return page;
    }
}
