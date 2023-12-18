package com.example.springboottest.domain.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class StudentInsertReq {

    @ApiModelProperty("学生姓名")
    @NotBlank
    private String name;
    @ApiModelProperty("学生年龄")
    @NotNull
    private Integer age;
    @ApiModelProperty("学生性别")
    private Integer sex;
    @ApiModelProperty("学生联系方式")
    @NotBlank
    private String phone;
    @ApiModelProperty("学生家庭住址")
    private String address;
}
