package com.zzx.graduation.controller;

import com.zzx.graduation.entity.Staff;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.service.StaffService;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class StaffController {

    //slf4j.Logger
    private  static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private StaffService staffService;

    /**
     * 如果id为空，则返表示方法查询所有staff
     * 如果id为一个具体数值，则表示查询某一个具体staff
     * 主要
     * @param id
     * @return
     */
    @PostMapping(value ="findStaff" )
    public ResponseMessage findStaff(Integer id){
        if(id==null){
            List<Staff> lists =  staffService.findAllStaff();
            if(lists==null){
                return new ResponseMessage<List<Staff>>(StateCode.SUCCESS_EMPTY,"empty",lists);
            }
            return new ResponseMessage<List<Staff>>(StateCode.SUCCESS,"查询成功",lists);
        }
            Staff staff =  staffService.findStaffById(id);
           List<Staff> lists =new ArrayList<>();
        if(staff == null){
            return new ResponseMessage<List<Staff>>(StateCode.SUCCESS_EMPTY,"empty",lists);
        }
        lists.add(staff);
        return new ResponseMessage<List<Staff>>(StateCode.SUCCESS,"empty",lists);
    }

    /**
     * 本方法根据name模糊查询
     * name为空表示查询所有
     * @param name
     * @return
     */
    @PostMapping(value = "findStaffByName")
    public ResponseMessage findStaffByName( String name){
        System.out.println(" -----------name:"+name);
        if(  null == name||"".equals(name.trim())){
            List<Staff> lists =  staffService.findAllStaff();
            if(lists==null){
                return new ResponseMessage<List<Staff>>(StateCode.SUCCESS_EMPTY,"empty",lists);
            }
            return new ResponseMessage<List<Staff>>(StateCode.SUCCESS,"查询成功",lists);
        }
        List<Staff> lists =staffService.findStaffByName(name);
        if(lists == null){
            return new ResponseMessage<List<Staff>>(StateCode.SUCCESS_EMPTY,"empty",lists);
        }
        return new ResponseMessage<List<Staff>>(StateCode.SUCCESS_EMPTY,"empty",lists);
    }

    /**
     * private Integer staffId;
     * 	private String staffName;
     * 	private String staffSex;
     * 	private Integer staffAge;
     *     private String staffPhone;
     *  private String staffAccountNum;//银行账户
     * 	private BigDecimal staffAccountMoney;//账户余额
     */
    /**
     * 更新员工
     * @param staff
     * @return
     */
    @PostMapping(value = "updateStaff")
    public ResponseMessage updateStaff(Staff staff){
        boolean flag = true;
        try {
            flag = staffService.updateStaff(staff);
        } catch (Exception e) {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"更新失败",false);
        }
        if(flag){
            return new ResponseMessage<Boolean>(StateCode.SUCCESS,"更新成功",true);
        }
        return new ResponseMessage<Boolean>(StateCode.ERROR,"更新失败",false);
    }

    /**
     * 添加员工
     * @param staff
     * @return
     */
    @PostMapping(value = "addStaff")
    public ResponseMessage addStaff(Staff staff){
        Boolean flag = true;
        try {
            flag = staffService.add(staff);
        } catch (Exception e) {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"添加失败",false);
        }
        if(flag){
            return new ResponseMessage<Boolean>(StateCode.SUCCESS,"添加成功",true);
        }
        return new ResponseMessage<Boolean>(StateCode.ERROR,"添加失败",false);
    }

     @PostMapping(value = "deleteStaffById")
    public ResponseMessage deleteStaffById(Integer id){
         boolean flag = true;
        try {
           flag =  staffService.deleteById(id);
        } catch (Exception e) {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"删除失败",false);
        }
        if(flag)
        {
            return new ResponseMessage<Boolean>(StateCode.SUCCESS,"删除成功",true);
        }
         return new ResponseMessage<Boolean>(StateCode.ERROR,"删除失败",false);
    }

    @PostMapping(value = "deleteStaffByIds")
    public ResponseMessage deleteStaffByIds(int[] ids){
            boolean[] check=null;
        try {
           check =  staffService.deleteByIds(ids);
        } catch (Exception e) {
            return new ResponseMessage<boolean[]>(StateCode.ERROR,"异常失败，全部回滚",null);
        }
        return new ResponseMessage<boolean[]>(StateCode.SUCCESS,"执行成功",check);
    }
}
