package com.example.springboottest.servcice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.UserInformation;
import com.example.springboottest.domain.req.UserInformationReq;


public interface UserInformationService {

    int save(UserInformation userInformation);

    int update(UserInformation userInformation);

    IPage<UserInformation> getList(UserInformationReq req);
}
