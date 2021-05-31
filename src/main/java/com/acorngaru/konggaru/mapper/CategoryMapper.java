package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category")
    List<Category> findAllCategories();

    @Select("select name from category where id = #{id}")
    String findCategoryNameById(@Param("id") int id);
}
