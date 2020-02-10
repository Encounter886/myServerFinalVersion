package com.zzx.graduation.repository;


import com.zzx.graduation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//继承jparepository操作数据库
public interface UserRepository extends JpaRepository<User,Integer> {

       User findByUsernameAndPassword(String username, String password);
       User findByUsername(String username);

       List<User> findAll();
}
