package com.example.springboottest.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Smile
 */
@Data
@Setter
@Getter
@TableName("user_trajectory")
public class UserTrajectory {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String userId;
    private Integer inHourId;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String cityName;
    private String countyName;
    private BigDecimal stayDuration;
    private BigDecimal stayViewpointDistance;
    private BigDecimal stayMallDistance;
    private BigDecimal homeLongitude;
    private BigDecimal homeLatitude;
    private BigDecimal workLongitude;
    private BigDecimal workLatitude;
    private String pDay;
}
