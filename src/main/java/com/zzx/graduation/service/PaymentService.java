package com.zzx.graduation.service;

import com.zzx.graduation.entity.Payment;
import com.zzx.graduation.entity.Salary;
import com.zzx.graduation.entity.Staff;
import com.zzx.graduation.repository.PaymentRepository;
import com.zzx.graduation.repository.SalaryRepository;
import com.zzx.graduation.repository.StaffRepository;
import com.zzx.graduation.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class PaymentService {

  @Autowired
     private PaymentRepository paymentRepository;


   @Transactional
   public synchronized   boolean  payMoney(String name, BigDecimal money) throws  Exception{
      Payment payment = new Payment();
           payment.setPaymentNum(money);
           payment.setPaymentPeople(name);
           payment.setPaymentTime(new DateFormat(new Date()).getDateString());
           paymentRepository.save(payment);
       System.out.println("------------付款成功");
       return true;
   }





    /**
     *     按照月份查询当月所有转账记录
     * @param time  格式为 2020-02
     */
    public List<Payment> findPaymentByMonth(String time) throws Exception{

        List<Payment> list = paymentRepository.findByPaymentTimeLike(time+"%");
        return list;
    }

    /**
     * 按照查询姓名
     * @param name
     * @return
     */
    public List<Payment> findPaymentByName(String name) throws Exception{
        List<Payment> list = paymentRepository.findByPaymentPeopleLike(name+"%");
        return list;
    }
    /**
     * 查询所有转账 记录
     */
    public  List<Payment> findAllPayment() throws Exception{
        List<Payment> salaries = paymentRepository.findAll();
        return salaries;
    }
}
