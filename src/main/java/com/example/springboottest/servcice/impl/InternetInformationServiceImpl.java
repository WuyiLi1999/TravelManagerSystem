package com.example.springboottest.servcice.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottest.domain.InternetInformation;
import com.example.springboottest.domain.req.InternetInformationReq;
import com.example.springboottest.mapper.InternetInformationMapper;
import com.example.springboottest.servcice.InternetInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Smile
 */
@Service
public class InternetInformationServiceImpl implements InternetInformationService {

    @Autowired
    private InternetInformationMapper internetInformationMapper;

    @Override
    public int insertInternetInformation(InternetInformation internetInformation) {
        return internetInformationMapper.insert(internetInformation);
    }

    @Override
    public IPage<InternetInformation> selectInternetInformation(InternetInformationReq req) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StrUtil.isNotBlank(req.getUserId())){
            queryWrapper.like("user_id",req.getUserId());
        }
        Page<InternetInformation> page=new Page<>(req.getPageNum(), req.getPageSize());
        return internetInformationMapper.selectPage(page,queryWrapper);
    }
}
