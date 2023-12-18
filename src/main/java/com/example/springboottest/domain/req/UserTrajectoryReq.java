package com.example.springboottest.domain.req;

import com.example.springboottest.domain.PageInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Smile
 */
@Data
@Getter
@Setter
public class UserTrajectoryReq extends PageInfo {
    private String userId;
    private String cityName;
    private String countyName;
    private String startDate;
    private String endDate;
}
