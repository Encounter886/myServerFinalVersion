package com.zzx.graduation.service;

import com.zzx.graduation.entity.InFood;
import com.zzx.graduation.entity.VegetablesTypes;
import com.zzx.graduation.repository.InFoodRepository;
import com.zzx.graduation.repository.VegetableTypesRepository;
import com.zzx.graduation.utils.DateFormat;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component//本service目前仅提供查询和添加功能 ，供删除功能只提供id
public class InFoodService {
    @Autowired
    InFoodRepository inFoodRepository;

    @Autowired
    VegetableTypesRepository vegetableTypesRepository;

    /**
     * 添加或者修改都可
     * @param inFood
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updateOrAddInFood(InFood inFood,Integer vId) throws Exception{
        VegetablesTypes vegetablesTypes = vegetableTypesRepository.findById(vId).get();
        inFood.setInTime(new DateFormat(new Date()).getDateString());
        inFood.setVegetablesTypesIn(vegetablesTypes);

        inFood.getVegetablesTypesIn().getListIn().add(inFood);
        inFoodRepository.save(inFood);
        return true;
    }

    /**
     * 按照inFoood表中的id 查询，应该用不到此方法
     * @param id
     * @return
     * @throws Exception
     */
    public InFood findInFoodById(Integer id)throws  Exception{
        return inFoodRepository.findById(id).get();
    }

    /**
     * A按照菜品名称查询所有入库
     * @param name
     * @return
     * @throws Exception
     */
    public List<InFood>  findInFoodByName(String name) throws Exception{
        return vegetableTypesRepository.findVegetablesTypesByFoodName(name).getListIn();

    }

    /**
     * 按照时间模糊查询
     * 2020-02这个样子
     * @param time
     * @return
     */
    public List<InFood>  findByTime(String time) throws Exception{
        return  inFoodRepository.findInFoodsByInTimeLike(time+"%");
    }

    /**
     * 查询所有入库菜品
     * @return
     * @throws Exception
     */
    public List<InFood> findAllInFood() throws Exception{
           return inFoodRepository.findAll();
    }

    /**
     * 根据id删除入库记录
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean deleteById(Integer id) throws Exception{
        InFood inFood = inFoodRepository.findById(id).get();
        inFood.getVegetablesTypesIn().getListIn().remove(inFood);
        inFoodRepository.deleteById(id);
        return true;
    }
}
