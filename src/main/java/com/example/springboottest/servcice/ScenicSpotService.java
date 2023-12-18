package com.example.springboottest.servcice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.ScenicSpot;
import com.example.springboottest.domain.req.ScenicSpotReq;
import com.example.springboottest.domain.req.ScenicSpotUpdateReq;
import com.sun.org.apache.xpath.internal.operations.Bool;


/**
 * @author Smile
 */
public interface ScenicSpotService {
    int  insertScenicSpot(ScenicSpot scenicSpot);
    IPage<ScenicSpot> queryList(ScenicSpotReq scenicSpot);
    Boolean deleteSpot(Long id,int flag);

    Boolean updateSpotById(ScenicSpotUpdateReq req);
}
