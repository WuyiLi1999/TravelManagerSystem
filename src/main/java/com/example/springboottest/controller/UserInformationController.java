package com.example.springboottest.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.common.DateUtil;
import com.example.springboottest.domain.Res.StudentRes;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.UserInformation;
import com.example.springboottest.domain.UserTrajectory;
import com.example.springboottest.domain.req.StudentReq;
import com.example.springboottest.domain.req.StudentUpdateReq;
import com.example.springboottest.domain.req.UserInformationReq;
import com.example.springboottest.mapper.UserInformationMapper;
import com.example.springboottest.servcice.UserInformationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.poi.ss.usermodel.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lwy
 */
@RestController
@RequestMapping("/userInformation")
@Validated
@Slf4j
public class UserInformationController {
    @Resource
    private UserInformationService userInformationService;

    @PostMapping("/save")
    public ResultInfo<Boolean> insertUserInformation(){
        String url="D:\\UserInformation.txt";
        int num=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(url));

            String line;
            while ((line=br.readLine())!=null){
                num++;
                line.trim();
                String [] data=line.split(",");
                UserInformation userInformation=new UserInformation();
                userInformation.setUserId(data[0]);

                userInformation.setAge((int) Double.parseDouble(data[1]));
                userInformation.setSex((int) Double.parseDouble(data[2]));
                userInformation.setCityName(data[3]);
                userInformation.setCountryName(data[4]);
                userInformation.setInnerDur((int)Double.parseDouble(data[5]));
                userInformation.setIsMarr((int)Double.parseDouble(data[6]));
                userInformation.setFertile((int)Double.parseDouble(data[7]));
                userInformation.setHaveOld((int)Double.parseDouble(data[8]));
                userInformation.setHaveCar((int)Double.parseDouble(data[9]));
                userInformation.setIncomeLevel((int)Double.parseDouble(data[10]));
                userInformation.setFirImelBrand(data[11]);
                userInformation.setFirImelPrice(Double.parseDouble(data[12]));
                userInformationService.save(userInformation);
            }

//            Workbook workbook = WorkbookFactory.create(new File(url));
//            // 假设数据在第一个sheet
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // 遍历行
//            Iterator<Row> rowIterator = sheet.iterator();
//            int num=0;
//            while (rowIterator.hasNext()) {
//                num++;
//                Row row = rowIterator.next();
//                if (num==1){
//                    continue;
//                }
//                if (StrUtil.isBlank(row.getCell(0).getStringCellValue())){
//                    continue;
//                }
//
//                UserInformation userInformation=new UserInformation();
//                userInformation.setUserId(row.getCell(0).getStringCellValue());
//                userInformation.setAge(StrUtil.isNotBlank(row.getCell(1).getStringCellValue())?Integer.parseInt(row.getCell(1).getStringCellValue()):18);
//                userInformation.setSex(StrUtil.isNotBlank(row.getCell(2).getStringCellValue())?Integer.parseInt(row.getCell(2).getStringCellValue()):1);
//                userInformation.setCityName(StrUtil.isNotBlank(row.getCell(3).getStringCellValue())?row.getCell(3).getStringCellValue():"");
//                userInformation.setCountryName(row.getCell(4).getStringCellValue());
//                userInformation.setInnerDur(StrUtil.isNotBlank(row.getCell(5).getStringCellValue())?Integer.parseInt(row.getCell(5).getStringCellValue()):1);
//                userInformation.setIsMarr(StrUtil.isNotBlank(row.getCell(6).getStringCellValue())?Integer.parseInt(row.getCell(6).getStringCellValue()):1);
//                userInformation.setFertile(StrUtil.isNotBlank(row.getCell(7).getStringCellValue())?Integer.parseInt(row.getCell(7).getStringCellValue()):1);
//                userInformation.setHaveOld(StrUtil.isNotBlank(row.getCell(8).getStringCellValue())?Integer.parseInt(row.getCell(8).getStringCellValue()):1);
//                userInformation.setHaveCar(StrUtil.isNotBlank(row.getCell(9).getStringCellValue())?Integer.parseInt(row.getCell(9).getStringCellValue()):1);
//                userInformation.setIncomeLevel(StrUtil.isNotBlank(row.getCell(10).getStringCellValue())?Integer.parseInt(row.getCell(10).getStringCellValue()):3);
//                userInformation.setFirImelBrand(row.getCell(11).getStringCellValue());
//                userInformation.setFirImelPrice(StrUtil.isNotBlank(row.getCell(12).getStringCellValue())?Double.parseDouble(row.getCell(12).getStringCellValue()):0.0d);
//            userInformationService.save(userInformation);


        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail("保存失败");
        }

        return ResultInfo.success(num>0?true:false);
    }
    @PostMapping("/update")
    public ResultInfo<Boolean> updateUserInformation(@RequestBody UserInformation userInformation) {
        Integer num =userInformationService.update(userInformation);
        return ResultInfo.success(num>0?true:false);
    }
    @PostMapping("/query_list")
    public ResultInfo<IPage> queryStudentList(@RequestBody UserInformationReq req) {
        return ResultInfo.success(userInformationService.getList(req));
    }
    @PostMapping("query_by_id")
    public ResultInfo<StudentRes> getStudentById(@RequestParam(value = "id",required = true) @NotBlank Long id) {
        return null;
    }
    @GetMapping("/loadDataToDateBase")
    public void loadUserInformationToDateBase(String url){
        userInformationService.batchInsertUserInformation(url);
    }

}
