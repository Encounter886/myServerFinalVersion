package com.zzx.graduation.controller;

import com.zzx.graduation.entity.VegetablesTypes;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VegetablesController {

    @Autowired
    VegetableService vegetableService;

    /**
     * 菜品种类 1：蔬菜类   2：肉类  3 ：豆制品  4：水果 5；面食
     * 添加或者修改vegetablestypes实体 入表
     * @param vegetablesTypes
     * @return
     */
    @PostMapping(value = "updateOrInsertVegetable")
    public ResponseMessage updateOrInsertVegetable(VegetablesTypes vegetablesTypes){
        boolean flag;
        String name = vegetablesTypes.getFoodName();
        VegetablesTypes v;
        try {
            v = vegetableService.findVegetableByName(name);
        } catch (Exception e) {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"执行失败",false);
        }

        if(null != v)
        {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"执行失败,已有此菜品",false);
        }
        try {
            flag = vegetableService.updateOrSaveVegetable(vegetablesTypes);
        } catch (Exception e) {

            return new ResponseMessage<Boolean>(StateCode.ERROR,"执行失败",false);
        }
        return new ResponseMessage<Boolean>(StateCode.SUCCESS,"执行成功",true);
    }

    /**
     * 删除菜品
     * @param id
     * @return
     */
    @PostMapping(value = "deleteVegetableById")
    public ResponseMessage deleteVegetableById(Integer id){
        boolean flag ;
        try {
            flag = vegetableService.deleteVegetableById(id);
        } catch (Exception e) {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"执行失败",false);
        }
        return new ResponseMessage<Boolean>(StateCode.SUCCESS,"执行成功",true);
    }

    /**
     * 查询所有菜品
     * @return
     */
    @PostMapping(value = "findAllVegetable")
    public ResponseMessage findAllVegetable(){
        List<VegetablesTypes> list=null;
        try {
            list = vegetableService.findAllVegetables();
        } catch (Exception e) {
            return new ResponseMessage< List<VegetablesTypes>>(StateCode.ERROR,"执行失败",null);
        }
        return new ResponseMessage< List<VegetablesTypes>>(StateCode.SUCCESS,"执行成功",list);
    }

}
