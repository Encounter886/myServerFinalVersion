package com.zzx.graduation.controller;

import com.zzx.graduation.entity.InFood;
import com.zzx.graduation.entity.VegetablesTypes;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.service.InFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InFoodController {

    @Autowired
    InFoodService inFoodService;


    /**
     * 更新或添加新入库的记录
     * @param inFood
     * @return
     */
    @PostMapping(value = "updateOrAddInFood")
    public ResponseMessage updateOrAddInFood(InFood inFood, Integer vegetableId ){
        boolean flag;
        try {
            flag = inFoodService.updateOrAddInFood(inFood,vegetableId);
        } catch (Exception e) {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"执行失败",false);
        }
        return new ResponseMessage<Boolean>(StateCode.SUCCESS,"执行成功",flag);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @PostMapping(value = "deleteInFoodById")
    public  ResponseMessage deleteInFoodById(Integer id) {
        boolean flag;
        try {
            flag = inFoodService.deleteById(id);
        } catch (Exception e) {
            return new ResponseMessage<Boolean>(StateCode.ERROR,"删除失败",false);
        }
        return new ResponseMessage<Boolean>(StateCode.SUCCESS,"删除成功",flag);
    }


    /**
     * 下面是查询操作 ！！！！
     */

    /**
     * 根据id 查询单个
     * @param id
     * @return
     */
    @PostMapping(value = "findInFoodById")
     public ResponseMessage findInFoodById(Integer id){
         InFood flag;
         try {
             flag = inFoodService.findInFoodById(id);
         } catch (Exception e) {
             return new ResponseMessage<InFood>(StateCode.ERROR,"查询失败",null);
         }
         return new ResponseMessage<InFood>(StateCode.SUCCESS,"查询成功",flag);
     }

    /**
     * 根据菜品名称模糊查询入库货物
     * @param name
     * @return
     */
     @PostMapping(value = "findInFoodByName")
       public  ResponseMessage findInFoodByName(String name){
           List<InFood> flag;
           try {
               flag = inFoodService.findInFoodByName(name);
           } catch (Exception e) {
               return new ResponseMessage< List<InFood>>(StateCode.ERROR,"查询失败",null);
           }
           return new ResponseMessage< List<InFood>>(StateCode.SUCCESS,"查询成功",flag);
       }

    /**
     * 根据时间进行模糊查询
     *时间格式为    2020-02
     * @param time
     * @return
     */
       @PostMapping(value = "findInFoodByTime")
    public ResponseMessage  findInFoodByTime(String time){
        List<InFood> flag;
        try {
            flag = inFoodService.findByTime(time);
        } catch (Exception e) {
            return new ResponseMessage< List<InFood>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage< List<InFood>>(StateCode.SUCCESS,"查询成功",flag);
    }

    /**
     * 查询所有入库记录
     * @return
     */
    @PostMapping(value = "findAllInFood")
     public ResponseMessage findAllInFood(){
         List<InFood> flag;
         try {
             flag = inFoodService.findAllInFood();
         } catch (Exception e) {
             return new ResponseMessage< List<InFood>>(StateCode.ERROR,"查询失败",null);
         }
         return new ResponseMessage< List<InFood>>(StateCode.SUCCESS,"查询成功",flag);
     }
}
