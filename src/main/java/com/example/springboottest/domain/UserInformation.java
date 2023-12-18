package com.example.springboottest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lwy
 */
@TableName(value = "user_information")
@Data
@Getter
@Setter
public class UserInformation {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userId;
    private Integer age;
    private Integer sex;
    private String cityName;
    private String countryName;
    private Integer innerDur;
    private Integer isMarr;
    private Integer fertile;
    private Integer haveOld;
    private Integer haveCar;
    private Integer incomeLevel;
    private String firImelBrand;
    private Double firImelPrice;
}
