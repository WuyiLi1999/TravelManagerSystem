package com.example.springboottest.domain.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentReq {
    private String name;
    private Integer startAge;
    private Integer endAge;
    private Integer sex;
}
