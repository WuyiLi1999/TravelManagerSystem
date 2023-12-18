package com.example.springboottest.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "统一返回结果")
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo<T> implements Serializable {

    @ApiModelProperty(value = "成功标识0=失败，1=成功")
    private Integer code;
    @ApiModelProperty(value = "返回数据对象")
    private T data;
    @ApiModelProperty(value = "描述信息")
    private String message;
    @ApiModelProperty(value = "错误描述信息")
    private String errorMessage;

    public static<T> ResultInfo<T> success(T data){
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(1);
        result.setData(data);
        result.setMessage("");
        return result;
    }
    public static<T> ResultInfo<T> fail(String errorMessage){
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(0);
        result.setData(null);
        result.setMessage(errorMessage);
        result.setErrorMessage(errorMessage);
        return result;
    }
}
