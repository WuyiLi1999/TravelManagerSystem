package com.example.springboottest.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String parseToPatten(String inputPatten, String outputPatten,String inputDate)  {
        SimpleDateFormat inputFormat=new SimpleDateFormat(inputPatten);
        SimpleDateFormat outputFormat=new SimpleDateFormat(outputPatten);
        try {
            Date date = inputFormat.parse(inputDate);
            String formattedDate = outputFormat.format(date);
            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // 或者返回一个默认值，表示解析失败
        }
    }
    public static String parseToPatten(String patten,Date inputDate){
        SimpleDateFormat outputFormat=new SimpleDateFormat(patten);
        String formattedDate = outputFormat.format(inputDate);
        return formattedDate;
    }
}
