package com.example.springboottest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.UserInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInformationMapper extends BaseMapper<UserInformation> {
}
