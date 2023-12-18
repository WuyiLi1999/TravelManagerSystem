package com.example.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.UserTrajectory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Smile
 */
@Mapper
public interface UserTrajectoryInformationMapper extends BaseMapper<UserTrajectory> {

    int batchInsertUserTrajectory(@Param("userTrajectoryList") List<UserTrajectory> userTrajectoryList);
}
