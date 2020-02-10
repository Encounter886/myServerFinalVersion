package com.zzx.graduation.service;

import com.zzx.graduation.entity.OutFood;
import com.zzx.graduation.entity.Salary;
import com.zzx.graduation.entity.Staff;
import com.zzx.graduation.entity.VegetablesTypes;
import com.zzx.graduation.repository.InFoodRepository;
import com.zzx.graduation.repository.OutFoodRepository;
import com.zzx.graduation.repository.StaffRepository;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 本类对可视化数据进行封装
 *
 */
@Component
public class DataVisualService {

    @Autowired
    VegetableService vegetableService;
    @Autowired
    StaffService staffService;

    @Autowired
    SalaryService salaryService;

    @Autowired
    OutFoodService outFoodService;
    @Autowired
    OutFoodRepository outFoodRepository;

    @Autowired
    InFoodService inFoodService;
    @Autowired
    InFoodRepository inFoodRepository;


    /**
     * 包含员工最近 几个月平均薪资
     * @param num
     * @return
     */
    public  Map<String, BigDecimal> findAverageSalaryByMonth(int num)throws Exception{
        List<String> list = DateFormat.getDateMonthNum(num);
        Map<String,BigDecimal> map = new LinkedHashMap<>();

        while (list.size()>0){
         String s =  list.remove(list.size()-1).toString();

            List<Salary>  salaries = salaryService.findSalaryByMonth(s);
            BigDecimal b = BigDecimal.valueOf(0);

            for (Salary sa:
                 salaries) {
                b=sa.getPayNum().add(b);
            }
            b=b.divide(BigDecimal.valueOf(salaries.size()),2);
            System.out.println("&&"+s+"&&");
            map.put(s,b);
        }//while
        return map;
    }



    public void findVegetabeInByTime_Day(String time){

    }

    public void findVegetableInByTime_Week(String time){

    }

    public void findVegetableInByTime_Month(String time){

    }


    /**
     * ！！！！！！！！！！！！！！！！！后期有时间可以考虑用钩子函数去重构代码，减少代码量！！！！
     * 时间格式 2020-02-10
     * 查询一天菜品的消耗量
     * @param time
     * @throws Exception
     */
    public List< Map<String,BigDecimal>> findVegetabeOutByTime_Day(String time)throws  Exception{

       //查询所有当天的记录
       List<OutFood> outFoods = outFoodService.findByTime(time);
        List<VegetablesTypes> vegetablesTypes= vegetableService.findAllVegetables();
        return TemplateFoodOut.newInstance().doSth(vegetablesTypes,outFoods);
    }

    /**
     * 按照周
     * @param beginTime  2020-02-01  ---》   2020-09-08
     * @param overTime
     * @return
     * @throws Exception
     */
    public List< Map<String,BigDecimal>> findVegetableOutByTime_Week(String beginTime ,String overTime) throws  Exception{
        List<OutFood> outFoods = outFoodService.findByTimeBeteen(beginTime+" 00:00:00",overTime+" 23:59:59");
        List<VegetablesTypes> vegetablesTypes = vegetableService.findAllVegetables();
       return TemplateFoodOut.newInstance().doSth(vegetablesTypes,outFoods);
    }


    /**
     * * 时间格式 2020-02 ------>   2020-03  按照月份查询
     * @param beginTime
     * @param overTime
     * @return
     * @throws Exception
     */
    public List< Map<String,BigDecimal>> findVegetableOutByTime_Month(String beginTime ,String overTime) throws  Exception{

        List<OutFood> outFoods = outFoodService.findByTimeBeteen(beginTime+" 00:00:00",overTime+" 23:59:59");
        List<VegetablesTypes> vegetablesTypes= vegetableService.findAllVegetables();
        return TemplateFoodOut.newInstance().doSth(vegetablesTypes,outFoods);
    }

    /**
     * 进行数据分析
     * @param responseMessage
     */
    public  List<Map.Entry<String,BigDecimal>>  analyzePoplationFood( List< Map<String,BigDecimal>> responseMessage)
            throws Exception {
        // 定义方式：   Map<String,BigDecimal> mapNum = new HashMap<>();//数量
        // lists.add(mapNum);
        //   lists.add(mapProportion);
           Map<String,BigDecimal> mapNum = responseMessage.get(0);
               //排序，根据值得大小降序排序得到降序 键值对
        List<Map.Entry<String,BigDecimal>> list = TemplateFoodOut.doSort(mapNum);
        return list;
    }

}
