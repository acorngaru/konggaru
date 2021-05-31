package com.acorngaru.konggaru.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.acorngaru.konggaru.model.Ingredient;

import java.util.List;

@Mapper
public interface IngredientMapper {

    @Select("select * from ingredient")
    List<Ingredient> findAllIngredients();

    @Select("select * from ingredient where id = #{id}")
    Ingredient findIngredientById(@Param("id") int id);

    @Select("select * from (select i.*, rownum as rn from (select * from ingredient where name like '%'||#{searchTerm}||'%' order by id) i " +
            "where rownum <= (#{currentPageNo} * #{rows})) " +
            "where ((#{currentPageNo} - 1) * #{rows}) < rn")
    List<Ingredient> findIngredients(@Param("currentPageNo") int currentPageNo, @Param("rows") int rows, @Param("searchTerm") String searchTerm);

    @Select("select count(*) from ingredient where name like '%'||#{searchTerm}||'%'")
    int countIngredients(@Param("searchTerm") String searchTerm);

    @Insert("insert into ingredient () " +
            "values (#{id}")
    int insert(Ingredient ingredient);
}