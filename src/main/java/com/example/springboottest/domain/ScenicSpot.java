package com.example.springboottest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Smile
 */
@Data
@Setter
@Getter
@TableName("scenic_spot")
public class ScenicSpot {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String spotName;
    private String address;
    private String coordinates;
    private String inquiryPhone;
    private String complaintPhone;
    private Integer dailyCapacity;
    private String ticketInfo;
    private String description;
    private String postalCode;
    private String spotType;
    private String openingHours;
    private String discountPolicy;
    private String spotLevel;
    private String administrativeCode;
    private Integer isDelete;
}
