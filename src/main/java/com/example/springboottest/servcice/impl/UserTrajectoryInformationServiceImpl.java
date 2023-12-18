package com.example.springboottest.servcice.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottest.common.DateUtil;
import com.example.springboottest.domain.UserTrajectory;
import com.example.springboottest.domain.req.UserTrajectoryReq;
import com.example.springboottest.mapper.UserTrajectoryInformationMapper;
import com.example.springboottest.servcice.UserTrajectoryInformationService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserTrajectoryInformationServiceImpl implements UserTrajectoryInformationService {

    @Autowired
    private UserTrajectoryInformationMapper userTrajectoryInformationMapper;

    @Override
    @Async("taskExecutor")
    public int batchSaveTrajectory(String url) {
        url="C:\\Users\\Smile\\Desktop\\trajectoryInformation.xlsx";
        int num=0;
        try{
            Workbook workbook = WorkbookFactory.create(new File(url));
            // 假设数据在第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            List<UserTrajectory> list=new ArrayList<>();
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
                list.add(userTrajectory);
                if(list.size()==200){
                    userTrajectoryInformationMapper.batchInsertUserTrajectory(list);
                    list=new ArrayList<>();
                }
//                userTrajectoryInformationMapper.insert(userTrajectory);

            }
            userTrajectoryInformationMapper.batchInsertUserTrajectory(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public IPage<UserTrajectory> getTrajectoryList(UserTrajectoryReq req) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StrUtil.isNotBlank(req.getUserId())){
            queryWrapper.like("user_id",req.getUserId());
        }
        if (StrUtil.isNotBlank(req.getCityName())){
            queryWrapper.eq("city_name",req.getCityName());
        }
        if (StrUtil.isNotBlank(req.getCountyName())){
            queryWrapper.eq("country_name",req.getCountyName());
        }
        if (StrUtil.isNotBlank(req.getStartDate())&&StrUtil.isNotBlank(req.getEndDate())){
            queryWrapper.between("p_day",req.getStartDate(),req.getEndDate());
        }
        queryWrapper.orderByDesc("id");
        Page<UserTrajectory> page=new Page<>(req.getPageNum(),req.getPageSize());

        return userTrajectoryInformationMapper.selectPage(page,queryWrapper);
    }
}
