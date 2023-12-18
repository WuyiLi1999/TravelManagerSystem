package com.example.springboottest.servcice.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottest.domain.UserTrajectory;
import com.example.springboottest.domain.req.UserTrajectoryReq;
import com.example.springboottest.mapper.UserTrajectoryInformationMapper;
import com.example.springboottest.servcice.UserTrajectoryInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTrajectoryInformationServiceImpl implements UserTrajectoryInformationService {

    @Autowired
    private UserTrajectoryInformationMapper userTrajectoryInformationMapper;

    @Override
    public int saveTrajectory(UserTrajectory userTrajectory) {
        return userTrajectoryInformationMapper.insert(userTrajectory);
    }

    @Override
    public IPage<UserTrajectory> getTrajectoryList(UserTrajectoryReq req) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StrUtil.isNotBlank(req.getUserId())){
            queryWrapper.like("user_id",req.getUserId());
        }
        if (StrUtil.isNotBlank(req.getCityName())){
            queryWrapper.eq("city_name",req.getCityName());
        }
        if (StrUtil.isNotBlank(req.getCountyName())){
            queryWrapper.eq("country_name",req.getCountyName());
        }
        if (StrUtil.isNotBlank(req.getStartDate())&&StrUtil.isNotBlank(req.getEndDate())){
            queryWrapper.between("p_day",req.getStartDate(),req.getEndDate());
        }
        queryWrapper.orderByDesc("id");
        Page<UserTrajectory> page=new Page<>(req.getPageNum(),req.getPageSize());

        return userTrajectoryInformationMapper.selectPage(page,queryWrapper);
    }
}
