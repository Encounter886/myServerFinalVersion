package com.zzx.graduation.utils;

import io.swagger.models.auth.In;

import java.util.LinkedList;
import java.util.List;

public class Desk {
    private static LinkedList<Integer> linkedList = new LinkedList<>();
    private  static final int Max_NUM = 30;//最多排队数量
    private Desk(){}

    //添加顾客排排队
    public static int  add(int i){
       if(null==linkedList||linkedList.size()<Max_NUM){
           linkedList.add(i);
           return i;
       }
       return 0;
    }

    //顾客取号操作
    public static  int get(){
        int index;
        if(linkedList.size()>0)
        {
            index = linkedList.removeFirst();
            return index;
        }
        return 0;
    }

    //当前排队顾客人数
    public static  int size(){
        return linkedList.size();
    }
}
