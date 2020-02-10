package com.zzx.graduation.service;

import com.zzx.graduation.entity.VegetablesTypes;
import com.zzx.graduation.repository.VegetableTypesRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VegetableService {

    @Autowired
    VegetableTypesRepository vegetableTypesRepository;

    /**
     * 添加或者修改 擦品种类信息
     * @param vegetablesTypes
     * @return
     * @throws Exception
     */
    public boolean updateOrSaveVegetable(VegetablesTypes vegetablesTypes) throws Exception{
        vegetableTypesRepository.save(vegetablesTypes);
        return true;
    }

    /**
     * 删除菜品种类信息 根据id
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteVegetableById(Integer id) throws  Exception{
        vegetableTypesRepository.deleteById(id);
        return true;
    }

    /**
     * 查询是所有菜品信息，暂时不分页
     * @return
     * @throws Exception
     */
    public List<VegetablesTypes> findAllVegetables() throws Exception{
        List<VegetablesTypes> list = vegetableTypesRepository.findAll();
        return list;
    }

    /**
     * 菜品名称查询
     * @param name
     * @return
     * @throws Exception
     */
    public VegetablesTypes findVegetableByName(String name)  throws  Exception{
        VegetablesTypes vegetablesTypes = vegetableTypesRepository.findVegetablesTypesByFoodName(name);
        return vegetablesTypes;
    }

    /**
     * 根据菜品id查询
     * @param id
     * @return
     * @throws Exception
     */
    public VegetablesTypes findVegetableById(Integer id)  throws  Exception{
        VegetablesTypes vegetablesTypes = vegetableTypesRepository.findById(id).get();
        return vegetablesTypes;
    }
}
