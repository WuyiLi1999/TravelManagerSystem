package com.example.springboottest.servcice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.UserInformation;
import com.example.springboottest.domain.req.UserInformationReq;


/**
 * @author 用户信息业务层
 */
public interface UserInformationService {
    /**
     * 单一加载用户数据
     * @param userInformation 用户信息
     * @return
     */
    int save(UserInformation userInformation);

    /**
     * 批量加载用户数据
     * @param url 文件路径
     */
    void batchInsertUserInformation(String url);

    /**
     * 更新用户数据
     * @param userInformation
     * @return
     */
    int update(UserInformation userInformation);

    /**
     * 获取用户信息列表
     * @param req
     * @return
     */
    IPage<UserInformation> getList(UserInformationReq req);
}
