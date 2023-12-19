package com.example.springboottest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.InternetInformation;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.req.InternetInformationReq;
import com.example.springboottest.servcice.InternetInformationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author Smile
 * 用户上网信息控制层
 */
@RestController
@RequestMapping("/internetInformation")
@Validated
@Slf4j
public class InternetInformationController {
    @Autowired
    private InternetInformationService internetInformationService;

    /**
     * 加载指定路径的Excel数据到数据库中
     */
    @GetMapping("/loadFileData")
    public void LoadFileDataToDataBase(@RequestParam(value = "url") String url){
        try{
            url="C:\\Users\\Smile\\Desktop\\InternetInfomation.xlsx";
            Workbook workbook = WorkbookFactory.create(new File(url));
            // 假设数据在第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                InternetInformation internetInformation=new InternetInformation();
                internetInformation.setUserId(row.getCell(0).getStringCellValue());
                internetInformation.setMonthlyMobileDataUsage(row.getCell(1).getCellType()==CellType.NUMERIC?row.getCell(1).getNumericCellValue():0.0);
                internetInformation.setMonthlyTravelAppVisits(row.getCell(2).getCellType()==CellType.NUMERIC?(int)row.getCell(2).getNumericCellValue():0);
                internetInformation.setMonthlyTravelAppActiveDays(row.getCell(3).getCellType()==CellType.NUMERIC?(int)row.getCell(3).getNumericCellValue():0);
                internetInformation.setMonthlyHotelAppVisits(row.getCell(4).getCellType()==CellType.NUMERIC?(int)row.getCell(4).getNumericCellValue():0);
                internetInformation.setMonthlyHotelAppActiveDays(row.getCell(5).getCellType()==CellType.NUMERIC?(int)row.getCell(5).getNumericCellValue():0);
                internetInformation.setMonthlyVideoAppVisits(row.getCell(6).getCellType()==CellType.NUMERIC?(int)row.getCell(6).getNumericCellValue():0);
                internetInformation.setMonthlyVideoAppActiveDays(row.getCell(7).getCellType()==CellType.NUMERIC?(int)row.getCell(7).getNumericCellValue():0);
                internetInformation.setMonthlyMusicAppVisits(row.getCell(8).getCellType()==CellType.NUMERIC?(int)row.getCell(8).getNumericCellValue():0);
                internetInformation.setMonthlyMusicAppActiveDays(row.getCell(9).getCellType()==CellType.NUMERIC?(int)row.getCell(9).getNumericCellValue():0);

                internetInformation.setMonthlyOnlineShoppingAppVisits(row.getCell(10).getCellType()==CellType.NUMERIC?(int)row.getCell(10).getNumericCellValue():0);
                internetInformation.setMonthlyOnlineShoppingAppActiveDays(row.getCell(11).getCellType()==CellType.NUMERIC?(int)row.getCell(11).getNumericCellValue():0);
                internetInformation.setMonthlyPhotographyAppVisits(row.getCell(12).getCellType()==CellType.NUMERIC?(int)row.getCell(12).getNumericCellValue():0);
                internetInformation.setMonthlyPhotographyAppActiveDays(row.getCell(13).getCellType()==CellType.NUMERIC?(int)row.getCell(13).getNumericCellValue():0);
                internetInformation.setMonthlyMapAppVisits(row.getCell(14).getCellType()==CellType.NUMERIC?(int)row.getCell(14).getNumericCellValue():0);
                internetInformation.setMonthlyMapAppActiveDays(row.getCell(15).getCellType()==CellType.NUMERIC?(int)row.getCell(15).getNumericCellValue():0);
                internetInformation.setMonthlyTravelPublicAccountVisits(row.getCell(16).getCellType()==CellType.NUMERIC?(int)row.getCell(16).getNumericCellValue():0);
                internetInformation.setMonthlyTravelPublicAccountActiveDays(row.getCell(17).getCellType()==CellType.NUMERIC?(int)row.getCell(17).getNumericCellValue():0);
                internetInformation.setMonthlyHotelPublicAccountVisits(row.getCell(18).getCellType()==CellType.NUMERIC?(int)row.getCell(18).getNumericCellValue():0);
                internetInformation.setMonthlyHotelPublicAccountActiveDays(row.getCell(19).getCellType()==CellType.NUMERIC?(int)row.getCell(19).getNumericCellValue():0);
                internetInformation.setMonthlyTravelKeywordSearches(row.getCell(20).getCellType()==CellType.NUMERIC?(int)row.getCell(20).getNumericCellValue():0);

                internetInformation.setMonthlyTravelKeywordSearchDays(row.getCell(21).getCellType()==CellType.NUMERIC?(int)row.getCell(21).getNumericCellValue():0);
                internetInformation.setMonthlyHotelKeywordSearches(row.getCell(22).getCellType()==CellType.NUMERIC?(int)row.getCell(22).getNumericCellValue():0);
                internetInformation.setMonthlyHotelKeywordSearchDays(row.getCell(23).getCellType()==CellType.NUMERIC?(int)row.getCell(23).getNumericCellValue():0);

                internetInformationService.insertInternetInformation(internetInformation);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取用户信息列表
     * @param req
     * @return
     */
    @PostMapping("/getInternetInformationList")
    public ResultInfo<IPage<InternetInformation>> getInternetInformationList(@RequestBody InternetInformationReq req){
        try {
            IPage<InternetInformation> internetInformationIPage=internetInformationService.selectInternetInformation(req);
            return ResultInfo.success(internetInformationIPage);
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail("查询失败");
        }
    }
}
