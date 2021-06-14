package com.acorngaru.konggaru.mapper;
import com.acorngaru.konggaru.model.IngredinetOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IngredientOrderMapper {

    @Insert("insert into ingredient_order (ingredient_id,ingredient_name,ingredient_quantity,created_at,addmission_at)" +
            "values (#{ingredient_id},#{ingredient_name},#{ingredient_quantity},#{created_at},#{addmission_at})")
    public int orderAddIngredient(IngredinetOrder ingredient_order);
    @Update("update ingredient_order set created_at = #{created_at} where ingredient_id = #{ingredient_id}")
    public int orderCreatedAt(Map<String,Object> map);
    @Update("update ingredient_order set addmission_at = #{data}")
    public int addMissionAt(String data);
    @Delete("delete from ingredient_order\n")
    public int ingredientOrderDel();
    @Select("select * from ingredient_order order by ingredient_id")
    public List<IngredinetOrder> selectAllOrder();
    @Update("update ingredient set quantity =(#{quantity}+quantity) where name = #{name}")
    public int updateIngredientAdd(Map<String, Object> map);
}
