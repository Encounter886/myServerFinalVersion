package com.zzx.graduation.controller;

import com.zzx.graduation.entity.Payment;
import com.zzx.graduation.entity.Salary;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.service.PaymentService;
import com.zzx.graduation.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    /**
     * ci
     * @param name 应为付款客户唯一标志符号
     * @param money
     * @return
     */
    @PostMapping(value = "payMoney")
public ResponseMessage payMoney(String name, BigDecimal money){
        boolean flag = true;
        try {
            flag = paymentService.payMoney(name,money);
        } catch (Exception e) {
            flag = false;
        }
        if(!flag){
            return new ResponseMessage<Boolean>(StateCode.ERROR,"付款失败",flag);
        }
        return new ResponseMessage<Boolean>(StateCode.SUCCESS,"付款成功",flag);
    }




    /**
     * 根据付款月份 模糊查询
     * @param month
     * @return
     */
    @PostMapping(value = "findPaymentByMonth")
    public ResponseMessage findPaymentByMonth(String  month){

        List<Payment> list;
        try {
            list= paymentService.findPaymentByMonth(month);
        } catch (Exception e) {
            return new ResponseMessage<List<Payment>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage<List<Payment>>(StateCode.SUCCESS,"查询成功",list);
    }

    /**
     * 根据付款人姓名模糊查询
     * @param name
     * @return
     */
    @PostMapping(value = "findPaymentByName")
    public ResponseMessage findPaymentByName(String  name){
        List<Payment> list;
        try {
            list= paymentService.findPaymentByName(name);
        } catch (Exception e) {
            return new ResponseMessage<List<Payment>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage<List<Payment>>(StateCode.SUCCESS,"查询成功",list);
    }
    /**
     * 查询所有付款记录
     * @return
     */
    @PostMapping(value = "findAllPayment")
    public ResponseMessage findAllPayment(){
        List<Payment> list;
        try {
            list= paymentService.findAllPayment();
        } catch (Exception e) {
            return new ResponseMessage<List<Payment>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage<List<Payment>>(StateCode.SUCCESS,"查询成功",list);
    }
}
