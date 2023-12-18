package com.example.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.UserTrajectory;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author Smile
 */
@Mapper
public interface UserTrajectoryInformationMapper extends BaseMapper<UserTrajectory> {
}
