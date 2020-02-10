package com.zzx.graduation.repository;


import com.zzx.graduation.entity.Salary;
import com.zzx.graduation.entity.VegetablesTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//继承jparepository操作数据库
public interface VegetableTypesRepository extends JpaRepository<VegetablesTypes,Integer> {

    VegetablesTypes findVegetablesTypesByFoodName(String name);

}
