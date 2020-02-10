package com.zzx.graduation.repository;


import com.zzx.graduation.entity.Salary;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//继承jparepository操作数据库
public interface SalaryRepository extends JpaRepository<Salary,Integer> {


   /* @Query(value = "select p from salary where p.salaryTime>start and p.salaryTime<end")
    List<Salary> findBySalaryTime(String start,String end);*/

   List<Salary> findBySalaryTimeLike(String time);
}
