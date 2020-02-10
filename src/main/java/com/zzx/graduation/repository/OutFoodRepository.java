package com.zzx.graduation.repository;


import com.zzx.graduation.entity.InFood;
import com.zzx.graduation.entity.OutFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//继承jparepository操作数据库
public interface OutFoodRepository extends JpaRepository<OutFood,Integer> {

    List<OutFood> findOutFoodsByOutTimeLike(String time);
    List<OutFood> findOutFoodsByOutTimeBetween(String beginTime,String overTime);
}
