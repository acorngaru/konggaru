package com.acorngaru.konggaru.mapper;


import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Ingredient_Order;
import com.acorngaru.konggaru.service.Ingredient_OrderService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IngredientPageMapper {

    public List<Ingredient> searchIngredient(HashMap<String, String> map);
    public List<Ingredient> allIngredient();
    public List<Ingredient_Order> selectAllOrder();
    public int updateIngredientAdd(Map<String,Object> map);
    public int countIngredientByName(String name);
    public int ingredientDel(int n);
    public int updateIngredient(Ingredient ingredient);
    public int create(Ingredient ingredient);
    public int ingredientDelAll(List<String> arrayList);
    public int orderAddIngredient(Ingredient_Order ingredient_order);
    public int orderCreatedAt(Map<String,Object> map);
    public int addMissionAt(String data);
    public int ingredientOrderDel();
}