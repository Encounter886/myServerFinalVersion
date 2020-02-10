package com.zzx.graduation.controller;

import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.service.DataVisualService;
import com.zzx.graduation.service.StaffService;
import com.zzx.graduation.service.VegetableService;
import com.zzx.graduation.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class DataVisualController {

    @Autowired
    DataVisualService dataVisualService;
    /**
     * 用于存储最近的一次菜品消耗占比以及数量，为数据分析提供便利，避免二次查询
     * 避免网络负载过大
     */
    public   static List< Map<String,BigDecimal>> responseMessage=null;
    /**
     * 包含员工最近 几个月平均薪资
     * @param num
     * @return
     */
    @PostMapping(value = "findAverageSalaryByMonth")
    public ResponseMessage findAverageSalaryByMonth( int num){
        Map<String, BigDecimal> map;
        try {
           map = dataVisualService.findAverageSalaryByMonth(num);
        } catch (Exception e) {
            return new ResponseMessage< Map<String, BigDecimal>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage< Map<String, BigDecimal>>(StateCode.SUCCESS,"查询成功",map);
    }


    /**
     * @param code   1: 按当天查询     2：按照周查询    3：按照月份查询
     * @param endTime   时间戳      2020-02-10  这个样子
     * @return
     */
    @PostMapping(value = "findOutFoodByTimeDayMonthWeekEchats")
    public ResponseMessage findOutFoodByTimeDayMonthWeekEchats(int code,String endTime){
        List< Map<String,BigDecimal>> vegetabeOutByTime=null;
        try {
            switch (code){
                case 1:
                 vegetabeOutByTime = dataVisualService.findVegetabeOutByTime_Day(endTime.substring(0, 10));
                 responseMessage =  vegetabeOutByTime;
                     return new ResponseMessage< List< Map<String,BigDecimal>> >(StateCode.SUCCESS,"查询成功",vegetabeOutByTime);
                case  2:
                    vegetabeOutByTime = dataVisualService.findVegetableOutByTime_Week(DateFormat.getDateWeekStart(endTime),endTime.substring(0, 10));
                    responseMessage = vegetabeOutByTime;
                     return new ResponseMessage< List< Map<String,BigDecimal>> >(StateCode.SUCCESS,"查询成功",vegetabeOutByTime);

                case 3:
                    vegetabeOutByTime = dataVisualService.findVegetableOutByTime_Month(DateFormat.getDateMonthStart(endTime).substring(0,10),endTime.substring(0,10));
                    responseMessage = vegetabeOutByTime;
                     return  new ResponseMessage< List< Map<String,BigDecimal>> >(StateCode.SUCCESS,"查询成功",vegetabeOutByTime);
                    default:
                        return new ResponseMessage<>(StateCode.NOT_FOUNT,"查询条件有误",null);
            }
        } catch (Exception e) {
           /* e.printStackTrace();*/
            return new ResponseMessage<>(StateCode.NOT_FOUNT,"查询为空",null);
        }
    }


    /**
     * 本方法实现分析最受欢迎的菜品，返回给前端（！注意：可以返回key值，可以让之前返回给前端的数据保存在redis中
     * 避免再次查询mysql耗费时间）
     * @return
     */
    @PostMapping(value = "findAnalyzePoplationFood")
    public ResponseMessage findAnalyzePoplationFood(){
        List<Map.Entry<String,BigDecimal>> list=null;
        try {
          list = dataVisualService.analyzePoplationFood(responseMessage);
            return new ResponseMessage<List<Map.Entry<String,BigDecimal>>>(StateCode.NOT_FOUNT,"计算失败",list);
        } catch (Exception e) {
            return new ResponseMessage<>(StateCode.NOT_FOUNT,"计算成功",null);
        }
    }


}
