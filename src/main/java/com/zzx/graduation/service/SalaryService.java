package com.zzx.graduation.service;

import com.zzx.graduation.entity.Salary;
import com.zzx.graduation.entity.Staff;
import com.zzx.graduation.repository.SalaryRepository;
import com.zzx.graduation.repository.StaffRepository;
import com.zzx.graduation.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class SalaryService {

   @Autowired
    private  SalaryRepository salaryRepository;
   @Autowired
   private StaffRepository staffRepository;

   private static final Integer companyId=1;//公司id为staff中的id :1

    /**
     * 转账业务,为子方法
     * @param idIn 收钱
     * @param idOut
     * @param money
     * @throws Exception
     */
   @Transactional
   public synchronized   boolean  pushMoney(Integer idOut, Integer idIn, BigDecimal money) throws  Exception{
            idOut = companyId;
       Staff staffIn = staffRepository.findById(idIn).get();
       Staff staffOut = staffRepository.findById(idOut).get();
       staffIn.setStaffAccountMoney(staffIn.getStaffAccountMoney().add(money));
        staffOut.setStaffAccountMoney(staffOut.getStaffAccountMoney().subtract(money));

        //建立关系
        Salary salary = new Salary();
        salary.setPayNum(money);
        salary.setSalaryTime(new DateFormat(new Date()).getDateString());
        salary.setState(1);
        salary.setSalary(staffIn);

        staffIn.getSalaries().add(salary);
       staffRepository.save(staffIn);
       staffRepository.save(staffOut);
       salaryRepository.save(salary);
       System.out.println("-------------------------转账成功");
       return true;
   }


    /**
     * 返回的是执行状态，1：执行成功   0：执行失败
     * @param outId  不为空
     * @param inId 不为空
     * @param money 不为空
     * @return
     */
   public boolean[] pushMoney(Integer outId, int[] inId, BigDecimal[] money){
       boolean flag = true;
       boolean[] check = new boolean[inId.length];
       for (int i=0; i<inId.length ; i++){
           try {
               check[i] = pushMoney(outId,inId[i],money[i]);
           } catch (Exception e) {
               check[i] = false;
               flag=false;
           }
       }
       return check;
   }


    /**
     * 通过staff的id查询其所有记录
     * @param id
     */
    public List<Salary> findSalaryByStaffId(Integer id) throws Exception{
       Staff staff = staffRepository.findById(id).get();
       List<Salary> list = staff.getSalaries();
       return list;
    }



    /**
     *     按照月份查询当月所有转账记录
     * @param time  格式为 2020-02
     */
    public List<Salary> findSalaryByMonth(String time){

        List<Salary> list = salaryRepository.findBySalaryTimeLike(time+"%");
        return list;
    }

    /**
     * 查询所有转账 记录
     */
    public  List<Salary> findAllSalary() throws Exception{
        List<Salary> salaries = salaryRepository.findAll();
        return salaries;
    }
}
