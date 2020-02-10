package com.zzx.graduation.controller;

import com.zzx.graduation.entity.InFood;
import com.zzx.graduation.entity.OutFood;
import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.service.InFoodService;
import com.zzx.graduation.service.OutFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OutFoodController {

    @Autowired
    private OutFoodService outFoodService;


    /**
     * 更新或添加新入库的记录
     * @param outFood
     * @return
     */
    @PostMapping(value = "updateOrAddOutFood")
    public ResponseMessage updateOrAddOutFood(OutFood outFood, Integer vegetableId ){
        boolean flag;
        try {
            flag = outFoodService.updateOrAddOutFood(outFood,vegetableId);
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
    @PostMapping(value = "deleteOutFoodById")
    public  ResponseMessage deleteOutFoodById(Integer id) {
        boolean flag;
        try {
            flag = outFoodService.deleteById(id);
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
    @PostMapping(value = "findOutFoodById")
     public ResponseMessage findOutFoodById(Integer id){
         OutFood flag;
         try {
             flag = outFoodService.findOutFoodById(id);
         } catch (Exception e) {
             return new ResponseMessage<OutFood>(StateCode.ERROR,"查询失败",null);
         }
         return new ResponseMessage<OutFood>(StateCode.SUCCESS,"查询成功",flag);
     }

    /**
     * 根据菜品名称模糊查询入库货物
     * @param name
     * @return
     */
     @PostMapping(value = "findOutFoodByName")
       public  ResponseMessage findOutFoodByName(String name){
           List<OutFood> flag;
           try {
               flag = outFoodService.findOutFoodByName(name);
           } catch (Exception e) {
               return new ResponseMessage< List<OutFood>>(StateCode.ERROR,"查询失败",null);
           }
           return new ResponseMessage< List<OutFood>>(StateCode.SUCCESS,"查询成功",flag);
       }

    /**
     * 根据时间进行模糊查询
     *时间格式为    2020-02
     * @param time
     * @return
     */
       @PostMapping(value = "findOutFoodByTime")
    public ResponseMessage  findOutFoodByTime(String time){
        List<OutFood> flag;
        try {
            flag = outFoodService.findByTime(time);
        } catch (Exception e) {
            return new ResponseMessage< List<OutFood>>(StateCode.ERROR,"查询失败",null);
        }
        return new ResponseMessage< List<OutFood>>(StateCode.SUCCESS,"查询成功",flag);
    }

    /**
     * 查询所有入库记录
     * @return
     */
    @PostMapping(value = "findAllOutFood")
     public ResponseMessage findAllOutFood(){
         List<OutFood> flag;
         try {
             flag = outFoodService.findAllOutFood();
         } catch (Exception e) {
             return new ResponseMessage< List<OutFood>>(StateCode.ERROR,"查询失败",null);
         }
         return new ResponseMessage< List<OutFood>>(StateCode.SUCCESS,"查询成功",flag);
     }
}
