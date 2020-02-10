package com.zzx.graduation.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ResponseMessage<T> {
    private Integer code;
    private String msg;
    private T data;

    private ResponseMessage(){}
    public ResponseMessage(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

     public void  set(Integer code, String msg, T data) {
         this.code = code;
         this.msg = msg;
         this.data = data;
     }
}
