package com.example.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.TravelPredict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lwy
 */
@Mapper
public interface TravelPredictMapper extends BaseMapper<TravelPredict> {
    /**
     * 添加数据到旅行预测表中
     * @param travelPredict
     * @return
     */
    int saveToDatabase(@Param("travelPredict") TravelPredict travelPredict);

    /**
     *
     * @param predictList
     */
    void batchInsertDataToDataBase(@Param("predictList") List<TravelPredict> predictList);
}
