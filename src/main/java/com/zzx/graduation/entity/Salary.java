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
public class Salary {
	@Id//主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
	@Column(name="salary_id")
	private Integer salaryId;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(cascade = { CascadeType.PERSIST})//级联操作
	@JoinColumn(name="in_staff_id")//收款人id
	private Staff salary = new Staff();


	@Column(name="payNum")
	private BigDecimal payNum;//发放金额

	@Column(name="salary_time")
	private String  salaryTime;//发放时间   yy-mm-dd hh:mm:ss

	@Column(name="state")
	private Integer  state;//发放状态， 0：还未发放  1：已发放
}
