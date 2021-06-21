package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ProductMapper {

    @Select("select * from product where category_id = #{categoryId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "imageUrl", column = "image_url"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_id",
                    javaType = String.class,
                    one = @One(select = "com.acorngaru.konggaru.mapper.CategoryMapper.findCategoryNameById",
                            fetchType = FetchType.EAGER)),
            @Result(property = "recipe", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.acorngaru.konggaru.mapper.UsedIngredientMapper.findUsedIngredientsByProductId",
                            fetchType = FetchType.EAGER))
    })
    List<Product> findProductsByCategoryId(@Param("categoryId") int categoryId);

    @Select({
            "<script>",
            "select * from (",
            "   select p.*, rownum as rn from (",
            "       select * from product",
            "       <if test = \"searchType == 'id'\">", // 상품 아이디를 통한 검색
            "       where category_id like '%'||#{searchTerm}||'%'",
            "       </if>",
            "       <if test = \"searchType == 'name'\">", // 상품 이름을 통한 검색
            "       where name like '%'||#{searchTerm}||'%'",
            "       </if>",
            "       <if test = \"sortBy == 'low'\">", // 정렬 기준
            "       order by price asc",
            "       </if>",
            "       <if test = \"sortBy == 'high'\">", // 정렬 기준
            "       order by price desc",
            "       </if>",
            "   ) p where rownum &lt;= (#{pageNo} * #{rows})",
            ")",
            "where ((#{pageNo} - 1) * #{rows}) &lt; rn",
            "</script>"
    })
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "imageUrl", column = "image_url"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_id",
                    javaType = String.class,
                    one = @One(select = "com.acorngaru.konggaru.mapper.CategoryMapper.findCategoryNameById",
                               fetchType = FetchType.EAGER)),
            @Result(property = "recipe", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.acorngaru.konggaru.mapper.UsedIngredientMapper.findUsedIngredientsByProductId",
                                 fetchType = FetchType.EAGER))
    })
    List<Product> findProducts(@Param("pageNo") int pageNo,
                               @Param("rows") int rows,
                               @Param("searchType") String searchType,
                               @Param("searchTerm") String searchTerm,
                               @Param("sortBy") String sortBy) throws Exception;

    @Select("select * from product where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "imageUrl", column = "image_url"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "categoryName", column = "category_id",
                    javaType = String.class,
                    one = @One(select = "com.acorngaru.konggaru.mapper.CategoryMapper.findCategoryNameById",
                            fetchType = FetchType.EAGER)),
            @Result(property = "recipe", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.acorngaru.konggaru.mapper.UsedIngredientMapper.findUsedIngredientsByProductId",
                            fetchType = FetchType.EAGER))
    })
    Optional<Product> findProductById(@Param("id") int id) throws Exception;

    @Select({
            "<script>",
            "select count(*) from product",
            "<if test = \"searchType == 'id'\">", // 상품 아이디를 통한 검색
            "where category_id like '%'||#{searchTerm}||'%'",
            "</if>",
            "<if test = \"searchType == 'name'\">", // 상품 이름을 통한 검색
            "where name like '%'||#{searchTerm}||'%'",
            "</if>",
            "</script>"
    })
    int countProducts(@Param("searchType") String searchType, @Param("searchTerm") String searchTerm) throws Exception;

    @Insert("insert into product (id, name, description, price, category_id, image_url) " +
            "values (#{id}, #{name}, #{description}, #{price}, #{categoryId}, #{imageUrl})")
    @SelectKey(statement = "select product_seq.currval from dual", resultType = int.class, keyProperty = "id", before = false)
    int insert(Product product) throws Exception;

    @Update({
            "<script>",
            "update product set",
            "name = #{name},",
            "description = #{description},",
            "price = #{price},",
            "category_id = #{categoryId}",
            "<if test = \"imageUrl != null\">", // imageUrl이 없다면
            ", image_url = #{imageUrl}",
            "</if>",
            "where id = #{id}",
            "</script>"
    })
    int update(Product product) throws Exception;

    @Delete("delete from product where id = #{id}")
    int delete(Product product) throws Exception;
}
