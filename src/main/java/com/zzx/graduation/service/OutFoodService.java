package com.zzx.graduation.service;

import com.zzx.graduation.entity.InFood;
import com.zzx.graduation.entity.OutFood;
import com.zzx.graduation.entity.VegetablesTypes;
import com.zzx.graduation.repository.InFoodRepository;
import com.zzx.graduation.repository.OutFoodRepository;
import com.zzx.graduation.repository.VegetableTypesRepository;
import com.zzx.graduation.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class OutFoodService {
    @Autowired
     private  OutFoodRepository outFoodRepository;

    @Autowired
    private  VegetableTypesRepository vegetableTypesRepository;

    /**
     * 添加或者修改都可
     * @param outFood
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean updateOrAddOutFood(OutFood outFood, Integer vId) throws Exception{
        VegetablesTypes vegetablesTypes = vegetableTypesRepository.findById(vId).get();
        outFood.setOutTime(new DateFormat(new Date()).getDateString());
        outFood.setVegetablesTypesOut(vegetablesTypes);

        outFood.getVegetablesTypesOut().getListOut().add(outFood);
        outFoodRepository.save(outFood);
        return true;
    }

    /**
     * 按照inFoood表中的id 查询，应该用不到此方法
     * @param id
     * @return
     * @throws Exception
     */
    public OutFood findOutFoodById(Integer id)throws  Exception{
        return outFoodRepository.findById(id).get();
    }

    /**
     * A按照菜品名称查询所有 chu库
     * @param name
     * @return
     * @throws Exception
     */
    public List<OutFood>  findOutFoodByName(String name) throws Exception{
        return vegetableTypesRepository.findVegetablesTypesByFoodName(name).getListOut();

    }

    /**
     * 按照时间模糊查询
     * 2020-02这个样子
     * @param time
     * @return
     */
    public List<OutFood> findByTime(String time) throws Exception{
        return  outFoodRepository.findOutFoodsByOutTimeLike(time+"%");
    }

    public List<OutFood> findByTimeBeteen(String beginTime,String overTime) throws Exception{
        return  outFoodRepository.findOutFoodsByOutTimeBetween(beginTime,overTime);
    }
    /**
     * 查询所有chu库菜品
     * @return
     * @throws Exception
     */
    public List<OutFood> findAllOutFood() throws Exception{
        return outFoodRepository.findAll();
    }

    /**
     * 根据id删除入库记录
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean deleteById(Integer id) throws Exception{
        OutFood outFood = outFoodRepository.findById(id).get();
        outFood.getVegetablesTypesOut().getListOut().remove(outFood);
        outFoodRepository.deleteById(id);
        return true;
    }
}
