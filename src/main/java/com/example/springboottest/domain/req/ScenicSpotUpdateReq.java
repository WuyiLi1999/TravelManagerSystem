package com.example.springboottest.domain.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ScenicSpotUpdateReq {
    private Long id;
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
    private Integer isDelete;
}
