package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Cart;
import com.acorngaru.konggaru.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {
	
	@Select("select * from cart where member_id = #{memberId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "productQuantity", column = "product_quantity"),
            @Result(property = "product", column = "product_id",
                    javaType = Product.class,
                    one = @One(select = "com.acorngaru.konggaru.mapper.ProductMapper.findProductById", 
                               fetchType = FetchType.EAGER))
    })
	List<Cart> findCartListByMemberId(int id) throws Exception;

	@Insert("insert into cart (id, product_id, member_id, product_quantity) " +
			"values (#{id}, #{productId}, #{memberId}, #{productQuantity})")
	int insert(Cart cart);

	@Update("update cart set product_quantity = #{productQuantity} where id = #{id}")
	int updateCart(Cart cart) throws Exception;

	@Delete("delete from cart where id = #{id}")
	int deleteCart(int id);
    
}
