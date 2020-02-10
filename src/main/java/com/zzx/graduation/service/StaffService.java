package com.zzx.graduation.service;

import com.zzx.graduation.entity.Staff;
import com.zzx.graduation.repository.SalaryRepository;
import com.zzx.graduation.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private SalaryRepository salaryRepository;


    public Staff findStaffById(Integer id){
        Staff  staff = null ;
       try{
            staff = staffRepository.findById(id).get();

       }catch (Exception e){
          return null;
       }
        return staff;
    }

    public List<Staff> findAllStaff(){
        List<Staff> staffs = staffRepository.findAll();
        return staffs;
    }

    public List<Staff> findStaffByName(String name){
        List<Staff> staffs = staffRepository.findByStaffNameLike("%"+name+"%");
        return staffs;
    }

    /**
     * 此方法位分页查询，  暂时不用
     * @param page 起始页
     * @param size 页面大小
     * @return
     */
    public Page<Staff> findStaffByName(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Staff> staffs = staffRepository.findAll(pageable);
        System.out.println(staffs.getContent());
        return staffs;
    }

    /**
     *
     * 修改员工信息
     * @param staff
     * @return
     * @throws Exception
     */
    public boolean updateStaff(Staff staff) throws  Exception{
        staffRepository.save(staff);
        return true;
    }

    /**
     * 增加员工信息
     * @param staff
     * @return
     * @throws Exception
     */
    public boolean add(Staff staff) throws  Exception{
        staffRepository.save(staff);
        return true;
    }

    /**
     * 根据id删除
     * @param id  staff的id
     */
    public boolean  deleteById(Integer id) throws  Exception{
        staffRepository.deleteById(id);
        return true;
    }

    /**
     * 批量删除
     * @param ids
     */
    @Transactional
    public boolean[] deleteByIds(int[] ids) throws Exception{
        boolean[] check =new boolean[ids.length];

        for(int i=0;i<ids.length;i++){
            check[i] = deleteById(ids[i]);
        }
        return check;
    }


}
