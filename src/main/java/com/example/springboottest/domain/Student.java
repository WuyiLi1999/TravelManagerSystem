package com.example.springboottest.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学生信息实体类
 * @author lwy
 */
@Data
@ApiModel("学生信息实体类")
public class Student {
    @ApiModelProperty("学生ID 主键")
    private Long id;
    @ApiModelProperty("学生姓名")
    private String name;
    @ApiModelProperty("学生年龄")
    private Integer age;
    @ApiModelProperty("学生性别")
    private Integer sex;
    @ApiModelProperty("学生联系方式")
    private String phone;
    @ApiModelProperty("学生家庭住址")
    private String address;
}
