package com.example.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.InternetInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Smile
 */
@Mapper
public interface InternetInformationMapper extends BaseMapper<InternetInformation> {
}
