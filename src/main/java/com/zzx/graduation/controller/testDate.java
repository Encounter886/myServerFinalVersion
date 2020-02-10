package com.zzx.graduation.controller;

import com.zzx.graduation.response.ResponseMessage;
import com.zzx.graduation.utils.DateFormat;
import javafx.scene.input.DataFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class testDate {
  /*  public static void main(String[] args) {
        HashMap<String,Integer> hs = new HashMap<>();
        hs.put("S1",68);
        hs.put("S2",78);
        hs.put("S3",48);
        hs.put("S4",88);
        hs.put("S5",98);
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(hs.entrySet());
        //collections工具类的排序方法
        Collections.sort(list,new Comparator<Map.Entry<String, Integer>>() {
            //重写比较器
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //compare方法重写中，返回正数，则两个对象位置交换，返回正数，不交换
                return o2.getValue().compareTo(o1.getValue()); //compareTo方法返回值为int型，返回正数则左侧大于右侧
            }
        });
        //遍历输出结果
        for (Map.Entry<String,Integer> m : list) {
            System.out.println(m.getKey()+": "+m.getValue());
        }
    }*/


   public static void main(String  []args){
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String timeFormat = sdf.format(time);
        System.out.println(timeFormat);

       String list = null;
       try {
           list = DateFormat.getDateMonthStart("2020-02-10");
       } catch (ParseException e) {
           e.printStackTrace();
       }


       System.out.println(new DateFormat(new Date()).getDateString());

       System.out.println(new Date().getYear()+" "+new Date().getMonth());
        System.out.println(new DateFormat(new Date()).getDateString());
    }

   //测试取号
   /* public static void main(String[] args) {
        DeskConsumer consumer = new DeskConsumer();
        for(int i=0;i<31;i++){
          Object  o = consumer.addConsumer();
            System.out.println(((ResponseMessage) o).getMsg());
        }
        for(int i=0;i<31;i++){
            Object o1 = consumer.getConsumer();
            System.out.println(((ResponseMessage) o1).getMsg());
        }
    }*/
}
