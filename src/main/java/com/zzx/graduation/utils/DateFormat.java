package com.zzx.graduation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateFormat {
    //用于日期转换
    private Date targetDate;
    public DateFormat(Date date){
         this.targetDate = date;
    }
    public String getDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String timeFormat = sdf.format(this.targetDate);
        return timeFormat;
    }

    /**
     * 0123456
     * 2020-02
     *
     *
     * @param num 过去几个月 包括当月
     * @return
     */
    public static List<String >   getDateMonthNum(int num){

        List<String > list = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());

        boolean flag = true;
           while(num>0){
               if(flag){
                   c.add(Calendar.MONTH, 0);
                   flag = false;
               }
               else {
                   c.add(Calendar.MONTH, -1);
               }

               Date y = c.getTime();
               String mon = format.format(y);
               System.out.println("过去一个月："+mon);
               num--;
               list.add(mon.substring(0,7));
           }
        System.out.println("结束----"+list);
        return list;
    }

    /**
     *
     * @param time   2020-02-10 查询输入时间一周前
     * @return
     */
    public static String   getDateWeekStart(String time) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去一年
        new Date();
        c.setTime(format.parse(time));
        c.add(Calendar.WEEK_OF_MONTH, -1);
        Date y = c.getTime();
        String mon = format.format(y);
        System.out.println("过去一周："+mon);

        return mon;
    }


    /**
     *
     * @param time   2020-02-10 查询输入时间一月前
     * @return
     */
    public static String   getDateMonthStart(String time) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去一年
        new Date();
        c.setTime(format.parse(time));
        c.add(Calendar.MONTH, -1);
        Date y = c.getTime();
        String mon = format.format(y);
        System.out.println("过去一个月："+mon);

        return mon;
    }
}
