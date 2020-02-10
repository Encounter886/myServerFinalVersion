package com.zzx.graduation.entity;

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
public class Payment {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Column(name="payment_id")
    private Integer paymentId;

    @Column(name="payment_people")
    private String  paymentPeople;//付款人

    @Column(name="payment_Num")
    private BigDecimal paymentNum;//付款金额

    @Column(name="payment_time")
    private String  paymentTime;//付款时间   yy-mm-dd hh:mm:ss

}
