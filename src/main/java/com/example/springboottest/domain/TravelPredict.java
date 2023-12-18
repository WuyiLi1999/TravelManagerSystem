package com.example.springboottest.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lwy
 * @description
 */
@Data
@Getter
@Setter
@TableName("predict_result")
public class TravelPredict {
    private Long id;
    private String userId;
    private String flag;
    public TravelPredict(){

    }
    public TravelPredict(String userId,String flag){
        this.userId=userId;
        this.flag=flag;
    }
}
