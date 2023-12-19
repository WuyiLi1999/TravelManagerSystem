package com.example.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lwy
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
