package com.zzx.graduation.repository;


import com.zzx.graduation.entity.InFood;
import com.zzx.graduation.entity.Salary;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//继承jparepository操作数据库
public interface InFoodRepository extends JpaRepository<InFood,Integer> {

    List<InFood> findInFoodsByInTimeLike(String time);

}
