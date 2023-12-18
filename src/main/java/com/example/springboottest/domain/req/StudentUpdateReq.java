package com.example.springboottest.domain.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentUpdateReq {
    @ApiModelProperty("学生ID 主键")
    private Long id;
    @ApiModelProperty("学生联系方式")
    private String phone;
    @ApiModelProperty("学生家庭住址")
    private String address;
}
