package com.example.springboottest.domain;

public class Result<T> {
    public static<T> ResultInfo<T> OK(Integer code,T data,String message,String errorMessage){
        return new ResultInfo<T>(code,data,message,errorMessage);
    }
}
