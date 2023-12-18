package com.example.springboottest.servcice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottest.domain.UserInformation;
import com.example.springboottest.domain.req.UserInformationReq;
import com.example.springboottest.mapper.UserInformationMapper;
import com.example.springboottest.servcice.UserInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationMapper userInformationMapper;
    @Override
    public int save(UserInformation userInformation) {
        return userInformationMapper.insert(userInformation);
    }

    @Override
    public int update(UserInformation userInformation) {
        return 0;
    }

    @Override
    public IPage<UserInformation> getList(UserInformationReq req) {
        QueryWrapper<UserInformation> queryWrapper=new QueryWrapper<>();
        if (req.getUserId()!=null){
            queryWrapper.like("user_id",req.getUserId());
        }
        if (req.getSex()!=null){
            queryWrapper.eq("sex",req.getSex());
        }
        if (req.getCountryName()!=null){
            queryWrapper.eq("country_name",req.getCountryName());
        }
        Page<UserInformation> page=new Page<>(req.getPageNum(),req.getPageSize());
        return userInformationMapper.selectPage(page,queryWrapper);
    }

}
