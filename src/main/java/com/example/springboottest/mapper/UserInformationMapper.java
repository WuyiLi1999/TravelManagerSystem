package com.example.springboottest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInformationMapper extends BaseMapper<UserInformation> {
    int batchInsertUserInformation(@Param("list") List<UserInformation> list);
}
