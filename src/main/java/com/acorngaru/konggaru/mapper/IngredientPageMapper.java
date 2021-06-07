package com.acorngaru.konggaru.mapper;


import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Ingredient_Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Mapper
@Repository
public interface IngredientPageMapper {

    public List<Ingredient> searchIngredient(HashMap<String, String> map);
    public List<Ingredient> allIngredient();
    public int countIngredientByName(String name);
    public int ingredientDel(int n);
    public int updateIngredient(Ingredient ingredient);
    public int create(Ingredient ingredient);
    public int ingredientDelAll(List<String> arrayList);
    @Insert(" insert into ingredient_order (ingredient_id,ingredient_name,ingredient_quantity,created_at,addmission_at)" +
            "        values (#{ingredient_id},#{ingredient_name},#{ingredient_quantity},#{created_at},#{addmission_at})")
    public int orderAddIngredient(int ingredient_id, String ingredient_name,
                                  int ingredient_quantity, String created_at,
                                    String addmission_at);

}