package com.acorngaru.konggaru.mapper;


import com.acorngaru.konggaru.model.Ingredient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IngredientMapper {

    @Select("select * from ingredient where id = #{id}")
    Ingredient findIngredientById(@Param("id") int id);
    List<Ingredient> searchIngredient(@Param("first") int first, @Param("last") int last, @Param("name") String name);
    List<Ingredient> allIngredient();
    int countIngredientByName(String name);
    int ingredientDel(int n);
    int updateIngredient(Ingredient ingredient);
    int create(Ingredient ingredient);
    int ingredientDelAll(List<Integer> arrayList);
}