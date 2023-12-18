package com.example.springboottest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.ScenicSpot;
import com.example.springboottest.domain.TravelPredict;
import com.example.springboottest.domain.req.ScenicSpotReq;
import com.example.springboottest.domain.req.ScenicSpotUpdateReq;
import com.example.springboottest.servcice.ScenicSpotService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author Smile
 */
@RestController
@RequestMapping("/scenicSpot")
@Validated
@Slf4j
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    /**
     * 加载指定路径的Excel数据到数据库中
     */
    @GetMapping("/loadFileData")
    public void LoadFileDataToDataBase(@RequestParam(value = "url") String url){
        url="C:\\Users\\Smile\\Desktop\\cate.xlsx";
        try(Workbook workbook = WorkbookFactory.create(new File(url))){
            // 假设数据在第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            int num=0;
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                num++;
                if (num==1){
                    continue;
                }

                ScenicSpot scenicSpot=new ScenicSpot();

                scenicSpot.setSpotName(row.getCell(0).getStringCellValue());
                scenicSpot.setAddress(row.getCell(1).getStringCellValue());
                scenicSpot.setCoordinates(row.getCell(2).getStringCellValue());

                if(row.getCell(3).getCellType()==CellType.NUMERIC){
                    String value = dataFormatter.formatCellValue(row.getCell(3));
                    scenicSpot.setInquiryPhone(value);
                }

//                scenicSpot.setInquiryPhone(row.getCell(3).getStringCellValue());

                if(row.getCell(4).getCellType()==CellType.NUMERIC){
                    String value = dataFormatter.formatCellValue(row.getCell(4));
                    scenicSpot.setComplaintPhone(value);
                }

//                scenicSpot.setComplaintPhone(row.getCell(4).getStringCellValue());

                if(row.getCell(5).getCellType()==CellType.NUMERIC){
                    String value = dataFormatter.formatCellValue(row.getCell(5));
                    scenicSpot.setDailyCapacity(Integer.parseInt(value));
                    System.out.println("daily:"+scenicSpot.getDailyCapacity());
                }


                if(row.getCell(6).getCellType()==CellType.NUMERIC){
                    String value = dataFormatter.formatCellValue(row.getCell(6));
                    scenicSpot.setComplaintPhone(value);
                }else {
                    scenicSpot.setTicketInfo(row.getCell(6).getStringCellValue());
                }

                scenicSpot.setDescription(row.getCell(7).getStringCellValue());

                if(row.getCell(8).getCellType()==CellType.NUMERIC){
                    String value = dataFormatter.formatCellValue(row.getCell(8));
                    scenicSpot.setPostalCode(value);
                }
//                scenicSpot.setPostalCode(row.getCell(8).getStringCellValue());

                scenicSpot.setSpotType(row.getCell(9).getStringCellValue());
                scenicSpot.setOpeningHours(row.getCell(10).getStringCellValue());

                scenicSpot.setDiscountPolicy(row.getCell(11).getStringCellValue());
                scenicSpot.setSpotLevel(row.getCell(12).getStringCellValue());

//                scenicSpot.setAdministrativeCode(row.getCell(13).getStringCellValue());

                if(row.getCell(13).getCellType()==CellType.NUMERIC){
                    String value = dataFormatter.formatCellValue(row.getCell(13));
                    scenicSpot.setAdministrativeCode(value);
                }
                if(row.getCell(14).getCellType()==CellType.NUMERIC){
                    String value = dataFormatter.formatCellValue(row.getCell(14));
                    scenicSpot.setIsDelete(Integer.parseInt(value));
                }
//                scenicSpot.setIsDelete(Integer.parseInt(row.getCell(14).getStringCellValue()));
                System.out.println(scenicSpot.getDailyCapacity()+":"+scenicSpot.getIsDelete());
                scenicSpotService.insertScenicSpot(scenicSpot);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/getSpotList")
    public ResultInfo<IPage<ScenicSpot>> getSpotList(@RequestBody ScenicSpotReq req){
        try {
            IPage<ScenicSpot> travelPredictList=scenicSpotService.queryList(req);
            return ResultInfo.success(travelPredictList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail("查询失败");
        }
    }

    @PostMapping("/updateSpotById")
    public ResultInfo<Boolean> updateSpotById(@RequestBody ScenicSpotUpdateReq req){
        return ResultInfo.success(scenicSpotService.updateSpotById(req));
    }

    @GetMapping("/deleteSpotById")
    public ResultInfo<Boolean> deleteSpot(@RequestParam Long id){
        return ResultInfo.success(scenicSpotService.deleteSpot(id,0));
    }

    @GetMapping("/cannelDeleteSpotById")
    public ResultInfo<Boolean> cannelDeleteSpot(@RequestParam Long id){
        return ResultInfo.success(scenicSpotService.deleteSpot(id,1));
    }
}
