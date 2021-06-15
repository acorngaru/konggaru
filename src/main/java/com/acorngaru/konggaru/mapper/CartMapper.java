package com.acorngaru.konggaru.mapper;

import java.util.List;

import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.acorngaru.konggaru.model.Cart;
import com.acorngaru.konggaru.model.Product;

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
	
	@Update("update cart set product_quantity = #{productQuantity} where id = #{id}")
	int updateCart(Cart cart) throws Exception;

	@Delete("delete from cart where id = #{id}")
	int deleteCart(int id) throws Exception;
    
}
