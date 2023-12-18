package com.example.springboottest.servcice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.UserTrajectory;
import com.example.springboottest.domain.req.UserTrajectoryReq;

/**
 * @author Smile
 */
public interface UserTrajectoryInformationService {

    int saveTrajectory(UserTrajectory userTrajectory);

    IPage<UserTrajectory> getTrajectoryList(UserTrajectoryReq req);
}
