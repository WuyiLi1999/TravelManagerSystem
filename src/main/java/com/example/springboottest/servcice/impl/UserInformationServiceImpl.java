package com.example.springboottest.servcice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottest.domain.UserInformation;
import com.example.springboottest.domain.req.UserInformationReq;
import com.example.springboottest.mapper.UserInformationMapper;
import com.example.springboottest.servcice.UserInformationService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Smile
 * 用户信息业务层实现
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationMapper userInformationMapper;
    /**
     * 单一加载用户数据
     * @param userInformation 用户信息
     * @return
     */
    @Override
    public int save(UserInformation userInformation) {
        return userInformationMapper.insert(userInformation);
    }
    /**
     * 批量加载用户数据
     * @param url 文件路径
     */
    @Override
    @Async("taskExecutor")
    public void batchInsertUserInformation(String url) {
        url="C:\\Users\\Smile\\Desktop\\userInformation1.xlsx";
        int num=0;
        try(Workbook workbook = WorkbookFactory.create(new File(url))){
            // 假设数据在第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            List<UserInformation> list=new ArrayList<>();
            for (Row row : sheet) {
                num++;
                if (num==1){
                    continue;
                }
                UserInformation userInformation=new UserInformation();

                userInformation.setUserId(row.getCell(0) != null&&row.getCell(0).getCellType()== CellType.STRING?row.getCell(0).getStringCellValue():"");
                userInformation.setAge(row.getCell(1)!=null&&row.getCell(1).getCellType()==CellType.NUMERIC?(int) row.getCell(1).getNumericCellValue():new Random().nextInt(19)+30);
                userInformation.setSex(row.getCell(2)!=null&&row.getCell(2).getCellType()==CellType.NUMERIC?(int) row.getCell(2).getNumericCellValue():new Random().nextInt(2));
                userInformation.setCityName(row.getCell(3)!=null&&row.getCell(3).getCellType()==CellType.STRING? row.getCell(3).getStringCellValue():"");
                userInformation.setCountryName(row.getCell(4)!=null&&row.getCell(4).getCellType()==CellType.STRING? row.getCell(4).getStringCellValue():"");
                userInformation.setInnerDur(row.getCell(5)!=null&&row.getCell(5).getCellType()==CellType.NUMERIC?(int) row.getCell(5).getNumericCellValue():new Random().nextInt(2));
                userInformation.setIsMarr(row.getCell(6)!=null&&row.getCell(6).getCellType()==CellType.NUMERIC?(int) row.getCell(6).getNumericCellValue():new Random().nextInt(2));
                userInformation.setFertile(row.getCell(7)!=null&&row.getCell(7).getCellType()==CellType.NUMERIC?(int) row.getCell(7).getNumericCellValue():new Random().nextInt(2));
                userInformation.setHaveOld(row.getCell(8)!=null&&row.getCell(8).getCellType()==CellType.NUMERIC?(int) row.getCell(8).getNumericCellValue():new Random().nextInt(2));
                userInformation.setHaveCar(row.getCell(9)!=null&&row.getCell(9).getCellType()==CellType.NUMERIC?(int) row.getCell(9).getNumericCellValue():new Random().nextInt(2));
                userInformation.setIncomeLevel(row.getCell(10)!=null&&row.getCell(10).getCellType()==CellType.NUMERIC?(int) row.getCell(10).getNumericCellValue():new Random().nextInt(2));
                userInformation.setFirImelBrand(row.getCell(11)!=null&&row.getCell(11).getCellType()==CellType.STRING?row.getCell(11).getStringCellValue():"");
                userInformation.setFirImelPrice(row.getCell(12)!=null&&row.getCell(12).getCellType()==CellType.NUMERIC?row.getCell(12).getNumericCellValue():0.0);
                list.add(userInformation);
                if(list.size()==200){
                    userInformationMapper.batchInsertUserInformation(list);
                    list=new ArrayList<>();
                }
            }

            userInformationMapper.batchInsertUserInformation(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 更新用户数据
     * @param userInformation
     * @return
     */
    @Override
    public int update(UserInformation userInformation) {
        return 0;
    }

    /**
     * 获取用户信息列表
     * @param req
     * @return
     */
    @Override
    public IPage<UserInformation> getList(UserInformationReq req) {
        QueryWrapper<UserInformation> queryWrapper=new QueryWrapper<>();
        if (req.getUserId()!=null){
            queryWrapper.like("user_id",req.getUserId());
        }
        if (req.getSex()!=null){
            queryWrapper.eq("sex",req.getSex());
        }
        if (req.getCountryName()!=null){
            queryWrapper.eq("country_name",req.getCountryName());
        }
        Page<UserInformation> page=new Page<>(req.getPageNum(),req.getPageSize());
        Page<UserInformation> userInformationPage = userInformationMapper.selectPage(page, queryWrapper);

        return userInformationPage;
    }

}
