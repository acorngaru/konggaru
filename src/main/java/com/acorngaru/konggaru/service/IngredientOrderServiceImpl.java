package com.acorngaru.konggaru.service;
import com.acorngaru.konggaru.mapper.IngredientOrderMapper;
import com.acorngaru.konggaru.model.IngredientOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class IngredientOrderServiceImpl implements IngredientOrderService {
    @Autowired
    IngredientOrderMapper mapper;

    public int orderAddIngredient(IngredientOrder ingredient_order) {return mapper.orderAddIngredient(ingredient_order);}
    public List<IngredientOrder> selectAllOrder() {return mapper.selectAllOrder();}
    public int orderCreatedAt(Map<String,Object>map) {return mapper.orderCreatedAt(map);}
    public  int addMissionAt(String data){return mapper.addMissionAt(data); }
    public  int updateIngredientAdd(Map<String,Object> map){return mapper.updateIngredientAdd(map); }
    public int ingredientOrderDel(){
        return mapper.ingredientOrderDel();
    }
}
