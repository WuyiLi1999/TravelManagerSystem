package com.example.springboottest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lwy
 */
@Data
@TableName("user")
public class User {
    @TableField(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;
    private String password;
}
