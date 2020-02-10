package com.zzx.graduation.controller;

import com.zzx.graduation.entity.Salary;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.service.SalaryService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping(value = "pushMoney")
public ResponseMessage pushMoneyById(Integer id, BigDecimal money){
        boolean flag = true;
        try {
            flag = salaryService.pushMoney(1,id,money);
        } catch (Exception e) {
            flag = false;
        }
        if(!flag){
            return new ResponseMessage<Boolean>(StateCode.ERROR,"转账失败",flag);
        }
        return new ResponseMessage<Boolean>(StateCode.SUCCESS,"转账成功",flag);
    }

    @PostMapping(value = "pushMoneyBatch")
    public ResponseMessage pushMoneyByIds(int[] ids, BigDecimal[] moneys){
        boolean[] flag ;
        try {
            flag = salaryService.pushMoney(1,ids,moneys);
        } catch (Exception e) {
            flag = null;
        }
        if(flag==null){
            return new ResponseMessage<boolean[]>(StateCode.ERROR,"全部转账失败",flag);
        }
        return new ResponseMessage<boolean[]>(StateCode.SUCCESS,"转账成功",flag);
    }

    /**
     *    通过staff的id查询其所有记录
     * @param id
     * @return
     */
  @PostMapping(value = "findSalaryById")
    public ResponseMessage findSalarysById(Integer id){
      List<Salary> list;
      try {
         list= salaryService.findSalaryByStaffId(id);
      } catch (Exception e) {
        return new ResponseMessage<List<Salary>>(StateCode.ERROR,"查询失败",null);
      }
      return new ResponseMessage<List<Salary>>(StateCode.SUCCESS,"查询成功",list);
  }


    /**
     *    code= 2 按照月份查询当月所有转账记录 格式为 2020-02
     *      code =3  查询所有转账 记录
     * @param month
     * @return
     */
    @PostMapping(value = "findSalaryByMonth")
    public ResponseMessage findSalarysByMonth(String  month){

        List<Salary> list;
        try {
            list= salaryService.findSalaryByMonth(month);
        } catch (Exception e) {
            return new ResponseMessage<List<Salary>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage<List<Salary>>(StateCode.SUCCESS,"查询成功",list);
    }

    /**
     * 查询所有记录
     * @return
     */
    @PostMapping(value = "findAllSalarys")
    public ResponseMessage findAllSalarys(){

        List<Salary> list;
        try {
            list= salaryService.findAllSalary();
        } catch (Exception e) {
            return new ResponseMessage<List<Salary>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage<List<Salary>>(StateCode.SUCCESS,"查询成功",list);
    }
}
