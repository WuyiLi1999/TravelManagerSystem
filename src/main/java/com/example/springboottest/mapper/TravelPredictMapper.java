package com.example.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.TravelPredict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lwy
 */
@Mapper
public interface TravelPredictMapper extends BaseMapper<TravelPredict> {
    int saveToDatabase(@Param("travelPredict") TravelPredict travelPredict);
}
