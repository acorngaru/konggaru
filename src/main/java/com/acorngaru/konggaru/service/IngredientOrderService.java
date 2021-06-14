package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.IngredinetOrder;

import java.util.List;
import java.util.Map;

public interface IngredientOrderService {
    int orderAddIngredient(IngredinetOrder ingredient_order);
    List<IngredinetOrder> selectAllOrder();
    int orderCreatedAt(Map<String,Object> map) ;
    int addMissionAt(String data);
    int updateIngredientAdd(Map<String,Object> map);
    int ingredientOrderDel();
}
