package com.zzx.graduation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
//@Proxy(lazy = true)//,默认开启懒加载
@Entity//jpa实体类和数据表映射
public class VegetablesTypes {
    @Id//主键     food
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Column(name="food_id")
    private Integer foodId;


    @Column(name="food_type")
    private  Integer foodType;//菜品种类 1：蔬菜类   2：肉类  3 ：豆制品 4：水果   5；面食
    @Column(name="food_name",unique = true)
    private String foodName;
    @Column(name="food_price")
    private BigDecimal foodPrice;//菜品单价

    @OneToMany(mappedBy = "vegetablesTypesIn",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})//入库菜品详细列表
    private List<InFood> listIn= new ArrayList<>();

    @OneToMany(mappedBy = "vegetablesTypesOut",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})//出库菜品列表
    private List<OutFood> listOut= new ArrayList<>();
}
