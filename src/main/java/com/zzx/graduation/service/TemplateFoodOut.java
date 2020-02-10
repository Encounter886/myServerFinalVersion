package com.zzx.graduation.service;

import com.zzx.graduation.entity.OutFood;
import com.zzx.graduation.entity.VegetablesTypes;
import com.zzx.graduation.repository.OutFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TemplateFoodOut {

    private static final TemplateFoodOut instance= new TemplateFoodOut();

    public static TemplateFoodOut newInstance(){
        return instance;
    }

    public  List< Map<String,BigDecimal>>  doSth( List<VegetablesTypes> vegetablesTypes,List<OutFood> outFoods) throws Exception{
        //查询所有菜品
       /* List<VegetablesTypes> vegetablesTypes= vegetableService.findAllVegetables();*/
        Map<String,BigDecimal> mapNum = new HashMap<>();//数量
        Map<String,BigDecimal> mapProportion = new HashMap<>();//比例
        BigDecimal total = BigDecimal.valueOf(0);//总数量
        //建立两个map
        for (VegetablesTypes v:
                vegetablesTypes) {
            mapNum.put(v.getFoodName(), BigDecimal.valueOf(0));
            mapProportion.put(v.getFoodName(), BigDecimal.valueOf(0));
        }
       /* //查询所有当天的记录00:00:00
        System.out.println("1111111111111111111111111111"+beginTime+"-----"+overTime);
        *//*List<OutFood> outFoods = outFoodService.findByTimeBeteen(beginTime+" 00:00:00",overTime+" 00:00:00");*//*
        System.out.println(outFoods);*/
        //遍历集合，并且根据键去存放值
        for (OutFood o:
                outFoods) {
            total=total.add(o.getOutFoodNum());
            mapNum.put(o.getVegetablesTypesOut().getFoodName(),o.getOutFoodNum().add(  mapNum.get(o.getVegetablesTypesOut().getFoodName()) ));
        }
        // 键
        String key = null;
        // 获取键集合的迭代器
        Iterator it = mapNum.keySet().iterator();
        while (it.hasNext()) {
            key = (String) it.next();
            mapProportion.put(key,mapNum.get(key).divide(total,4, RoundingMode.DOWN));
        }//至此，两个map都已经存好了
        List< Map<String,BigDecimal>> lists = new ArrayList<>();
        lists.add(mapNum);
        lists.add(mapProportion);
        return lists;
    }


    /**
     * 比较，把hashmap按值排序
     * @param hs
     */
    public  static List<Map.Entry<String,BigDecimal>> doSort(Map<String,BigDecimal> hs) {

        List<Map.Entry<String,BigDecimal>> list = new ArrayList<Map.Entry<String,BigDecimal>>(hs.entrySet());
        //collections工具类的排序方法
        Collections.sort(list,new Comparator<Map.Entry<String, BigDecimal>>() {
            //重写比较器
            @Override
            public int compare(Map.Entry<String, BigDecimal> o1, Map.Entry<String, BigDecimal> o2) {
                //compare方法重写中，返回正数，则两个对象位置交换，返回正数，不交换
                return o2.getValue().compareTo(o1.getValue()); //compareTo方法返回值为int型，返回正数则左侧大于右侧
            }
        });
        //遍历输出结果
       /* for (Map.Entry<String,BigDecimal> m : list) {
            System.out.println(m.getKey()+": "+m.getValue());
        }*/
        return list;
    }



}
