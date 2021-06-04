package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.UsedIngredient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsedIngredientMapper {

    @Select("select * from used_ingredient where product_id = #{productId} order by id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "ingredientId", column = "ingredient_id"),
            @Result(property = "usage", column = "usage"),
            @Result(property = "ingredient", column = "ingredient_id",
                    javaType = Ingredient.class,
                    one = @One(select = "com.acorngaru.konggaru.mapper.IngredientMapper.findIngredientById"))
    })
    List<UsedIngredient> findUsedIngredientsByProductId(@Param("productId") int productId);

    @Insert("insert into used_ingredient (id, product_id, ingredient_id, usage) " +
            "values (#{id}, #{productId}, #{ingredientId}, #{usage})")
    int insert(UsedIngredient usedIngredient);

    @Update("update used_ingredient set " +
            "usage = #{usage}" +
            "where id = #{id}")
    int update(UsedIngredient usedIngredient);

    @Delete("delete from used_ingredient where id = #{id}")
    int delete(UsedIngredient usedIngredient);

    @Delete("delete from used_ingredient where product_id = #{productId}")
    int deleteByProductId(@Param("productId") int productId);
}
