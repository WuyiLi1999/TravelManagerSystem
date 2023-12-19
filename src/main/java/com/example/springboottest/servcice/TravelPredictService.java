package com.example.springboottest.servcice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.TravelPredict;

import java.util.List;

/**
 * @author lwy
 * @Description 出行概率预测数据业务层
 */
public interface TravelPredictService {

    /**
     * 保存预测结果数据
     * @param travelPredict
     * @return
     */
    int saveToDatabase(TravelPredict travelPredict);

    /**
     * 分页查询预测结果数据
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<TravelPredict> selectPredictList(String userId, Integer pageNum, Integer pageSize);

    /**
     * 异步批量保存到数据库中
     * @param predictList
     */
    void batchInsertDataToDataBase(List<TravelPredict> predictList);
}
