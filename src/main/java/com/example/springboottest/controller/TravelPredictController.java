package com.example.springboottest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.TravelPredict;
import com.example.springboottest.servcice.TravelPredictService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author lwy
 * @Descpript: 出行概率预测数据控制层
 */
@RestController
@RequestMapping("/TravelPredict")
@Validated
@Slf4j
public class TravelPredictController {

    @Autowired
    private TravelPredictService travelPredictService;

    /**
     * 加载指定路径的Excel数据到数据库中
     */
    @GetMapping("/loadFileData")
    public ResultInfo<Boolean> LoadFileDataToDataBase(@RequestParam(value = "url") String url){
        url="C:\\Users\\Smile\\Desktop\\result_predict.xlsx";
        try(Workbook workbook = WorkbookFactory.create(new File(url))){
            // 假设数据在第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            int num=0;
            List<TravelPredict> predictList=new ArrayList<>();
            for (Row row : sheet) {
                num++;
                if(num==1){
                    continue;
                }
                // user_id 列对应的索引为 0
                Cell userIdCell = row.getCell(0);
                // flag 列对应的索引为 1
                Cell flagCell = row.getCell(1);

                String userId = userIdCell.getStringCellValue();
                String flag = flagCell.getStringCellValue();
                TravelPredict result=new TravelPredict(userId,flag);
                predictList.add(result);
                if (predictList.size()==400){
                    // 将 userId 和 flag 数据列表保存到数据库
                    travelPredictService.batchInsertDataToDataBase(predictList);
                    predictList=new ArrayList<>();
                }
            }
            travelPredictService.batchInsertDataToDataBase(predictList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultInfo.success(true);
    }

    @GetMapping("/getPredictList")
    public ResultInfo<IPage<TravelPredict>> getPredictList(String userId,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        try {
            IPage<TravelPredict> travelPredictList=travelPredictService.selectPredictList(userId,pageNum,pageSize);
            return ResultInfo.success(travelPredictList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail("查询失败");
        }
    }

}
