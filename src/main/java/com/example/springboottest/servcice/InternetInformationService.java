package com.example.springboottest.servcice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottest.domain.InternetInformation;
import com.example.springboottest.domain.req.InternetInformationReq;

/**
 * @author Smile
 */
public interface InternetInformationService {
    int insertInternetInformation(InternetInformation internetInformation);

    IPage<InternetInformation> selectInternetInformation(InternetInformationReq req);
}
