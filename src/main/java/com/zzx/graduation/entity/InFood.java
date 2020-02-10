package com.zzx.graduation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
//@Proxy(lazy = true)//,默认开启懒加载
@Entity//jpa实体类和数据表映射
public class InFood {
    @Id//主键     food
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Column(name="in_food_id")
    private Integer inFoodId;

    @Column(name="in_food_pay")
    private BigDecimal inFoodPay;//进货总金额

    @Column(name="in_food_num")
    private int inFoodNum;//进货总数量

    @Column(name="in_food_time")
    private String  inTime;//入库时间   yy-mm-dd hh:mm:ss


    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST})//级联操作
    @JoinColumn(name="in_food_vegetables_id")//关联菜品种类的id
    private VegetablesTypes vegetablesTypesIn = new VegetablesTypes();
}
