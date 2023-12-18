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
@Getter
@Setter
@TableName("internet_information")
public class InternetInformation {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userId;
    private Double monthlyMobileDataUsage;
    private int monthlyTravelAppVisits;
    private int monthlyTravelAppActiveDays;
    private int monthlyHotelAppVisits;
    private int monthlyHotelAppActiveDays;
    private int monthlyVideoAppVisits;
    private int monthlyVideoAppActiveDays;
    private int monthlyMusicAppVisits;
    private int monthlyMusicAppActiveDays;
    private int monthlyOnlineShoppingAppVisits;
    private int monthlyOnlineShoppingAppActiveDays;
    private int monthlyPhotographyAppVisits;
    private int monthlyPhotographyAppActiveDays;
    private int monthlyMapAppVisits;
    private int monthlyMapAppActiveDays;
    private int monthlyTravelPublicAccountVisits;
    private int monthlyTravelPublicAccountActiveDays;
    private int monthlyHotelPublicAccountVisits;
    private int monthlyHotelPublicAccountActiveDays;
    private int monthlyTravelKeywordSearches;
    private int monthlyTravelKeywordSearchDays;
    private int monthlyHotelKeywordSearches;
    private int monthlyHotelKeywordSearchDays;
}
