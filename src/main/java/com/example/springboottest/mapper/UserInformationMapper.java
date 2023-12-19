package com.example.springboottest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Smile
 * 用户信息数据管理层
 */
@Mapper
public interface UserInformationMapper extends BaseMapper<UserInformation> {
    /**
     * 批量加载用户数据
     * @param list 用户信息列表
     * @return
     */
    int batchInsertUserInformation(@Param("list") List<UserInformation> list);
}
