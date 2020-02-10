package com.zzx.graduation.controller;



import com.zzx.graduation.entity.User;
import com.zzx.graduation.repository.UserRepository;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.service.UserCRUD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;

import java.util.List;


//@Transactional
@RestController
@CrossOrigin
@Transactional
public class UserController {

    //slf4j.Logger
    private  static final Logger log = LoggerFactory.getLogger(UserController.class);


@Autowired
UserCRUD userService;//Service




@RequestMapping(value = "/loginByUserName",method = RequestMethod.POST)
public ResponseMessage UserLoginByName(@RequestParam("username") String username,
                                       @RequestParam("password")String password){
              System.out.println("进入controller层loginbyname:"+username+"----"+password);
             User user = userService.LoginByName(username,password);
              if(user==null)
              {
                  return  new ResponseMessage<User>(404,"登陆失败",user);
              }
              else
              {
                  return   new ResponseMessage<User>(200,"登录成功",user);
              }
}




}
