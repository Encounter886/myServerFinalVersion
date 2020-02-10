package com.zzx.graduation.repository;


import com.zzx.graduation.entity.Staff;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//继承jparepository操作数据库
public interface StaffRepository extends JpaRepository<Staff,Integer> {
      List<Staff> findByStaffNameLike(String staffName);


    Page<Staff> findByStaffNameLike(String staffName, Pageable pageable);


}
