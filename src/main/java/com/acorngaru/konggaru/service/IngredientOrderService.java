package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.IngredientOrder;

import java.util.List;
import java.util.Map;

public interface IngredientOrderService {
    int orderAddIngredient(IngredientOrder ingredient_order);
    List<IngredientOrder> selectAllOrder();
    int orderCreatedAt(Map<String,Object> map) ;
    int addMissionAt(String data);
    int updateIngredientAdd(Map<String,Object> map);
    int ingredientOrderDel();
}
