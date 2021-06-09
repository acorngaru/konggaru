package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.IngredientPageMapper;
import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Ingredient_Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class Ingredient_OrderService {

    @Autowired
    IngredientPageMapper mapper;

    public int orderAddIngredient(Ingredient_Order ingredient_order) {
        int n = mapper.orderAddIngredient(ingredient_order);
        return n;
    }

    public List<Ingredient_Order> selectAllOrder() {
        return mapper.selectAllOrder();
    }

    public int orderCreatedAt(Map<String,Object>map) {
        int n = mapper.orderCreatedAt(map);
        return n;
    }
    public  int addMissionAt(String data){
        log.info("service 실행됨");
        int n = mapper.addMissionAt(data);
        log.info("mapper 실행됨"+n);
        return n;
    }

    public  int updateIngredientAdd(Map<String,Object> map){
        return mapper.updateIngredientAdd(map);

    }

    public int ingredientOrderDel(){
        return mapper.ingredientOrderDel();
    }

}
