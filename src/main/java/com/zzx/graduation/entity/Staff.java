package com.zzx.graduation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
//@Proxy(lazy = true)//,默认开启懒加载
@Entity//jpa实体类和数据表映射
//与salary是一对多的关系
public class Staff {
	@Id//主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
	@Column(name="staff_id")
	private Integer staffId;

	@Column(name="staff_name")
	private String staffName;

	@Column(name="staff_sex")
	private String staffSex;

	@Column(name="staff_age")
	private Integer staffAge;


	@Column(name="staff_phone")
    private String staffPhone;


	@Column(name="staff_account_num")
 private String staffAccountNum;//银行账户

	@Column(name="staff_account_money")
	private BigDecimal staffAccountMoney;//账户余额

	@OneToMany(mappedBy = "salary",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})//工资列表
	private List<Salary> salaries= new ArrayList<>();
}
