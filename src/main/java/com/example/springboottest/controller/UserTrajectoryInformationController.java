package com.example.springboottest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.common.DateUtil;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.UserTrajectory;
import com.example.springboottest.domain.req.UserTrajectoryReq;
import com.example.springboottest.servcice.UserTrajectoryInformationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author Smile
 */
@RestController
@RequestMapping("/trajectoryInformation")
@Validated
@Slf4j
public class UserTrajectoryInformationController {

    @Autowired
    private UserTrajectoryInformationService userTrajectoryInformationService;
    /**
     * 加载指定路径的Excel数据到数据库中
     */
    @GetMapping("/loadFileData")
    public void LoadFileDataToDataBase(@RequestParam(value = "url") String url){
        url="C:\\Users\\Smile\\Desktop\\trajectoryInformation.xlsx";
        try{
            Workbook workbook = WorkbookFactory.create(new File(url));
            // 假设数据在第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            int num=0;
            for (Row row : sheet) {
                num++;
                if (num==1){
                    continue;
                }
                UserTrajectory userTrajectory=new UserTrajectory();
                userTrajectory.setUserId(row.getCell(0).getStringCellValue());
                userTrajectory.setInHourId(row.getCell(1) != null &&row.getCell(1).getCellType()==CellType.NUMERIC?(int)row.getCell(1).getNumericCellValue():0);
                userTrajectory.setLongitude(row.getCell(2) != null &&row.getCell(2).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(2))):new BigDecimal(0.0));
                userTrajectory.setLatitude(row.getCell(3) != null &&row.getCell(3).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(3))):new BigDecimal(0.0));
                userTrajectory.setCityName(row.getCell(4).getStringCellValue());
                userTrajectory.setCountyName(row.getCell(5).getStringCellValue());
                userTrajectory.setStayDuration(row.getCell(6) != null &&row.getCell(6).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(6))):new BigDecimal(0.0));
                userTrajectory.setStayViewpointDistance(row.getCell(7) != null &&row.getCell(7).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(7))):new BigDecimal(0.0));
                userTrajectory.setStayMallDistance(row.getCell(8) != null &&row.getCell(8).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(8))):new BigDecimal(0.0));
                userTrajectory.setHomeLongitude(row.getCell(9) != null &&row.getCell(9).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(9))):new BigDecimal(0.0));
                userTrajectory.setHomeLatitude(row.getCell(10) != null &&row.getCell(10).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(10))):new BigDecimal(0.0));
                userTrajectory.setWorkLongitude(row.getCell(11) != null &&row.getCell(11).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(11))):new BigDecimal(0.0));
                userTrajectory.setWorkLatitude(row.getCell(12) != null &&row.getCell(12).getCellType()==CellType.NUMERIC? new BigDecimal(dataFormatter.formatCellValue(row.getCell(12))):new BigDecimal(0.0));
                userTrajectory.setPDay(DateUtil.parseToPatten("yyyy/M/d","yyyy-MM-dd",dataFormatter.formatCellValue(row.getCell(13))));

                userTrajectoryInformationService.saveTrajectory(userTrajectory);

            }
            System.out.println(num);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @PostMapping("/getTrajectoryInformationList")
    public ResultInfo<IPage<UserTrajectory>> getTrajectoryInformationList(@RequestBody UserTrajectoryReq req){
        try {
            IPage<UserTrajectory> userTrajectoryIPage=userTrajectoryInformationService.getTrajectoryList(req);
            return ResultInfo.success(userTrajectoryIPage);
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail("查询失败");
        }
    }
}
