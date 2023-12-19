package com.example.springboottest.servcice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.UserInformation;
import com.example.springboottest.domain.req.UserInformationReq;

import java.util.List;


public interface UserInformationService {

    int save(UserInformation userInformation);

    int batchInsertUserInformation(String url);

    int update(UserInformation userInformation);

    IPage<UserInformation> getList(UserInformationReq req);
}
