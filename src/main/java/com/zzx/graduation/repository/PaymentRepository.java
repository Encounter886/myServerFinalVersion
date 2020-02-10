package com.zzx.graduation.repository;


import com.zzx.graduation.entity.Payment;
import com.zzx.graduation.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//继承jparepository操作数据库
public interface PaymentRepository extends JpaRepository<Payment,Integer> {




   List<Payment> findByPaymentPeopleLike(String name);
   List<Payment> findByPaymentTimeLike(String time);
}
