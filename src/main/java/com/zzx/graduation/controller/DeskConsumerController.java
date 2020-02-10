package com.zzx.graduation.controller;

import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.response.StateCode;
import com.zzx.graduation.utils.Desk;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeskConsumerController {
 private  static  int index =1;//当前号

    /**
     * 顾客加一，添加进排队队列
     * @return
     */
    @PostMapping(value = "addConsumer")
    public ResponseMessage addConsumer(){
     int i =  Desk.add(index++);
     if( i!=0 ){
         return new ResponseMessage<Integer>(StateCode.SUCCESS,"排队成功，号为："+i,i);
     }
    return new ResponseMessage<Integer>(StateCode.SUCCESS,"排队失败，号满",0);
}

    /**
     * 叫号就餐
     * @return
     */
    @PostMapping(value = "getConsumer")
    public ResponseMessage getConsumer(){
        int i =  Desk.get();
        if( i!=0 ){
            return new ResponseMessage<Integer>(StateCode.SUCCESS,"请号为："+i+"顾客准备就餐",i);
        }
        return new ResponseMessage<Integer>(StateCode.SUCCESS,"失败",0);
    }

}
