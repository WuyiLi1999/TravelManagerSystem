package com.example.springboottest.domain.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.springboottest.domain.PageInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Smile
 */
@Data
@Setter
@Getter
public class ScenicSpotReq extends PageInfo {
    private String spotName;
    private String address;
    private String spotLevel;
}
