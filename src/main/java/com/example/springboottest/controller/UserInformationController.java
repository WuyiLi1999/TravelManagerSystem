package com.example.springboottest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.UserInformation;
import com.example.springboottest.domain.req.UserInformationReq;
import com.example.springboottest.servcice.UserInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author lwy
 * @description 用户信息管理控制层
 */
@RestController
@RequestMapping("/userInformation")
@Validated
@Slf4j
public class UserInformationController {
    @Resource
    private UserInformationService userInformationService;

    /**
     * 单一加载用户数据
     * @param url 文件路径
     * @return
     */
    @GetMapping("/save")
    public ResultInfo<Boolean> insertUserInformation(String url){
        url="D:\\UserInformation.txt";
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

        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail("保存失败");
        }

        return ResultInfo.success(num>0?true:false);
    }

    /**
     * 用户信息更新
     * @param userInformation 更新的用户信息
     * @return
     */
    @PostMapping("/update")
    public ResultInfo<Boolean> updateUserInformation(@RequestBody UserInformation userInformation) {
        Integer num =userInformationService.update(userInformation);
        return ResultInfo.success(num>0?true:false);
    }

    /**
     * 查询用户信息列表
     * @param req 查询条件
     * @return
     */
    @PostMapping("/query_list")
    public ResultInfo<IPage> queryStudentList(@RequestBody UserInformationReq req) {
        return ResultInfo.success(userInformationService.getList(req));
    }

    /**
     * 批量异步加载用户数据
     * @param url 文件路径
     * @return
     */
    @GetMapping("/loadDataToDateBase")
    public ResultInfo<Boolean> loadUserInformationToDateBase(String url){
        userInformationService.batchInsertUserInformation(url);
        return ResultInfo.success(true);
    }

}
