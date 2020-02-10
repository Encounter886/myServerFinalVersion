package com.zzx.graduation.service;


import com.zzx.graduation.entity.User;
import com.zzx.graduation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserCRUD {

    @Autowired
    private UserRepository userRepository  ;

    public User LoginByName(String username,String password){
        User user = userRepository.findByUsernameAndPassword(username,password);
        System.out.println("loginbyname查询模块结果"+user);
        if(user != null)
        {
            return user;
        }
        System.out.println("登录失败");
        return null;
    }

}
