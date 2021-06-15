package com.acorngaru.konggaru.mapper;
import com.acorngaru.konggaru.model.IngredientOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IngredientOrderMapper {

    @Insert("insert into ingredient_order (ingredient_id,ingredient_name,ingredient_quantity,created_at,addmission_at)" +
            "values (#{ingredientId},#{ingredientName},#{ingredientQuantity},#{createdAt},#{addmissionAt})")
    public int orderAddIngredient(IngredientOrder ingredient_order);
    @Update("update ingredient_order set created_at = #{createdAt} where ingredient_id = #{ingredientId}")
    public int orderCreatedAt(Map<String,Object> map);
    @Update("update ingredient_order set addmission_at = #{data}")
    public int addMissionAt(String data);
    @Delete("delete from ingredient_order\n")
    public int ingredientOrderDel();
    @Select("select * from ingredient_order ")
    @Results({
            @Result(property = "ingredientId", column = "ingredient_id"),
            @Result(property = "ingredientName", column = "ingredient_name"),
            @Result(property = "ingredientQuantity", column = "ingredient_quantity"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "addmission_at", column = "addmissionAt"),
    })
    public List<IngredientOrder> selectAllOrder();
    @Update("update ingredient set quantity =(#{quantity}+quantity) where name = #{name}")
    public int updateIngredientAdd(Map<String, Object> map);
}
