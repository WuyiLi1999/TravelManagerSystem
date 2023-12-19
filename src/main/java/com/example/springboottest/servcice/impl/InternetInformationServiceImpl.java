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
 * 用户上网信息业务管理层
 */
@Service
public class InternetInformationServiceImpl implements InternetInformationService {

    @Autowired
    private InternetInformationMapper internetInformationMapper;

    /**
     * 导入用户上网数据
     * @param internetInformation
     * @return
     */
    @Override
    public int insertInternetInformation(InternetInformation internetInformation) {
        return internetInformationMapper.insert(internetInformation);
    }

    /**
     * 查询用户上网信息列表
     * @param req 查询条件
     * @return
     */
    @Override
    public IPage<InternetInformation> selectInternetInformation(InternetInformationReq req) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StrUtil.isNotBlank(req.getUserId())){
            queryWrapper.like("user_id",req.getUserId());
        }
        queryWrapper.orderByDesc("id");
        Page<InternetInformation> page=new Page<>(req.getPageNum(), req.getPageSize());
        return internetInformationMapper.selectPage(page,queryWrapper);
    }
}
