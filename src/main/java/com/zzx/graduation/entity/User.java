package com.zzx.graduation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 *
 */
@Getter
@Setter
@ToString
//@Proxy(lazy = true)//,默认开启懒加载
@Entity//jpa实体类和数据表映射
//@Table(name="tb_user")//指定和哪个表对应，省略就采用默认user
//@JsonIgnoreProperties(value = {"fans","visitors"})//需要一一列出需要忽略转json的属性，
// 还是在属性上面添加好一点
public class User  {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="userType")
    private  Integer userType;// 0,普通用户  1，管理员



}
